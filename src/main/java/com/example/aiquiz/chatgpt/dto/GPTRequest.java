package com.example.aiquiz.chatgpt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GPTRequest {
    private String model;
    private List<Message> messages;
    private int max_tokens;
    private double temperature;

    public GPTRequest(String prompt) {
        this.model = "gpt-4";
        this.messages = List.of(new Message("user", prompt));
        this.max_tokens = 500;
        this.temperature = 0.7;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Message {
        private String role;
        private String content;
    }
}
