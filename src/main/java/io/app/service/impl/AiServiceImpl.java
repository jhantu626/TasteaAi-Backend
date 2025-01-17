package io.app.service.impl;

import io.app.dto.ApiResponse;
import io.app.service.AiChatService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class AiServiceImpl implements AiChatService {
    @Value("${gemini.apiKey}")
    private String apiKey;

    @Override
    public ApiResponse generateChat(String prompt) throws IOException, InterruptedException {
        String uri="https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key="+apiKey;
        String requestBody = "{"
                + "\"contents\": [{"
                + "\"parts\": [{"
                + "\"text\": \"" + prompt + "\""
                + "}]"
                + "}]"
                + "}";

        HttpRequest request=HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpClient client=HttpClient.newHttpClient();
        HttpResponse<String> response=client.send(request,HttpResponse.BodyHandlers.ofString());
        if (response.statusCode()!=200){
            return ApiResponse.builder()
                    .status(false)
                    .message("Sorry Something went wrong server")
                    .build();
        }
        return ApiResponse.builder()
                .status(true)
                .message(response.body())
                .build();
    }
}