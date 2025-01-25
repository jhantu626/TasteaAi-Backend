package io.app.controller;

import io.app.dto.ApiResponse;
import io.app.dto.AuthBody;
import io.app.dto.ResponseToken;
import io.app.service.impl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthServiceImpl service;

    @PostMapping("/register")
    public ResponseEntity<ResponseToken> register(@RequestBody AuthBody authBody){
        return new ResponseEntity<>(service.register(authBody), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseToken> login(@RequestBody AuthBody authBody){
        return ResponseEntity.ok(service.login(authBody));
    }

    @DeleteMapping
    public ApiResponse deleteAllUser(){
        return service.deleteAllUser();
    }

}