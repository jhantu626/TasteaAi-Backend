package io.app.service.impl;

import io.app.dto.ApiResponse;
import io.app.dto.AuthBody;
import io.app.dto.ResponseToken;
import io.app.exception.DuplicateFoundException;
import io.app.exception.ResourceNotFoundException;
import io.app.model.User;
import io.app.repository.UserRepository;
import io.app.service.AuthService;
import io.app.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository repository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseToken register(AuthBody authBody) {
        boolean isUserRegisterd=repository.existsByEmail(authBody.getEmail());
        if(isUserRegisterd){
            throw new DuplicateFoundException("User already registerd");
        }
        User user=User.builder()
                .email(authBody.getEmail())
                .password(passwordEncoder.encode(authBody.getPassword()))
                .build();
        User savedUser=repository.save(user);
        String token=jwtService.generateToken(savedUser);

        return ResponseToken.builder()
                .status(true)
                .token(token)
                .build();
    }

    @Override
    public ResponseToken login(AuthBody authBody) {
        User user=repository.findByEmail(authBody.getEmail())
                .orElseThrow(()->new ResourceNotFoundException("User is not registered"));
        boolean isPasswordMatched=passwordEncoder.matches(authBody.getPassword(),user.getPassword());
        if(!isPasswordMatched){
            throw new DuplicateFoundException("Wrong Password!");
        }
        String token=jwtService.generateToken(user);
        return ResponseToken.builder()
                .token(token)
                .status(true)
                .build();
    }

    public ApiResponse deleteAllUser() {
        repository.deleteAll();
        return ApiResponse.builder()
                .message("Deleted All")
                .status(true)
                .build();
    }
}
