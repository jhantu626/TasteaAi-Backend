package io.app.service;

import io.app.dto.ApiResponse;

import java.io.IOException;
import java.net.MalformedURLException;

public interface AiChatService {
    public ApiResponse generateChat(String prompt) throws IOException, InterruptedException;
}
