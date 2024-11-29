package com.example.aiquiz.chatgpt.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class GPTService {
    private static final String API_KEY = System.getenv("OPENAI_API_KEY");
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    public GPTQuizResponse generateQuiz(String basePrompt) throws Exception {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        GPTRequest request = new GPTRequest(basePrompt);
        String requestBody = objectMapper.writeValueAsString(request);

        RequestBody body = RequestBody.create(requestBody, MediaType.parse("application/json"));
        Request requestObject = new Request.Builder()
                .url(API_URL)
                .post(body)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(requestObject).execute()) {
            if (response.isSuccessful()) {
                return objectMapper.readValue(response.body().string(), GPTQuizResponse.class);
            } else {
                throw new Exception("Failed to generate quiz: " + response.body().string());
            }
        }
    }

    @Getter
    @Setter
    public static class GPTRequest {
        private String model = "gpt-4";
        private GPTMessage[] messages;

        public GPTRequest(String prompt) {
            this.messages = new GPTMessage[]{new GPTMessage("system", prompt)};
        }
    }

    @Getter
    @Setter
    public static class GPTMessage {
        private String role;
        private String content;

        public GPTMessage(String role, String content) {
            this.role = role;
            this.content = content;
        }
    }

    @Getter
    @Setter
    public static class GPTQuizResponse {
        private GPTChoice[] choices;

        @Getter
        @Setter
        public static class GPTChoice {
            private GPTMessage message;

            @Getter
            @Setter
            public static class GPTMessage {
                private String content; // 문제 내용
                private String title;   // 문제 제목
                private String type;    // 문제 유형
            }
        }
    }
}
