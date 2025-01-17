package io.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.app.dto.ApiResponse;
import io.app.service.impl.AiServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/ai")
@RequiredArgsConstructor
@Slf4j
public class AiController {
    private final AiServiceImpl aiService;


    @PostMapping("/generate")
    public ResponseEntity<ApiResponse> generate(@RequestParam(name = "prompt") String prompt) throws IOException, InterruptedException {
        log.info("Entered inside ai generate");
        ApiResponse result=aiService.generateChat(prompt);
        return ResponseEntity.ok(result);
    }



}
