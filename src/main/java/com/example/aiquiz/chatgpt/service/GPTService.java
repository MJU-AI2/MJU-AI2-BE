package com.example.aiquiz.chatgpt.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class GPTService {

    private static final String API_KEY = System.getenv("OPENAI_API_KEY");
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    public String generateQuiz(String basePrompt) throws Exception {
        OkHttpClient client = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();

        String fullPrompt = basePrompt + "\n\n" +
                "Respond in JSON format with the following structure:\n" +
                "{\n" +
                "  \"question\": \"\",\n" +
                "  \"choices\": [\"\", \"\", \"\", \"\"],  // Optional for non-multiple-choice questions\n" +
                "  \"answer\": \"\"  // Correct answer\n" +
                "}\n\n" ;


        GPTRequest requestPayload = new GPTRequest("gpt-4", fullPrompt);
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
                throw new Exception("API call failed: " + response.code() + " - " + response.message());
            }
        }
    }

    @Getter
    @Setter
    static class GPTRequest {
        private String model;
        private GPTMessage[] messages;

        public GPTRequest(String model, String prompt) {
            this.model = model;
            this.messages = new GPTMessage[]{
                    new GPTMessage("system", "You are a helpful assistant that generates quizzes."),
                    new GPTMessage("user", prompt)
            };
        }

    }
    @Getter
    @Setter
    static class GPTMessage {
        private String role;
        private String content;

        public GPTMessage(String role, String content) {
            this.role = role;
            this.content = content;
        }

    }
    @Getter
    @Setter
    static class GPTResponse {
        private GPTChoice[] choices;

        public String getGeneratedText() {
            if (choices != null && choices.length > 0) {
                return choices[0].getMessage().getContent();
            }
            return null;
        }
        @Getter
        @Setter
        static class GPTChoice {
            private GPTMessage message;

        }
        @Getter
        @Setter
        static class GPTMessage {
            private String role;
            private String content;

        }
    }
}
