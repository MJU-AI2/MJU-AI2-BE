package com.example.aiquiz.chatgpt.service;

import com.example.aiquiz.chatgpt.dto.GPTRequest;
import com.example.aiquiz.chatgpt.dto.GPTResponse;
import lombok.Value;
import okhttp3.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class GPTService {

    //@Value("${openai.api.key}")
    private String apiKey;

    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
/*
    public String generateQuiz(String prompt) throws Exception {
        OkHttpClient client = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();

        GPTRequest requestPayload = new GPTRequest(prompt);
        String jsonPayload = objectMapper.writeValueAsString(requestPayload);

        RequestBody body = RequestBody.create(jsonPayload, MediaType.parse("application/json"));

        Request request = new Request.Builder()
                .url(API_URL)
                .header("Authorization", "Bearer " + API_KEY)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                GPTResponse gptResponse = objectMapper.readValue(response.body().string(), GPTResponse.class);
                return gptResponse.getGeneratedText();
            } else {
                throw new Exception("API call failed: " + response.code());
            }
        }

    }*/
}
