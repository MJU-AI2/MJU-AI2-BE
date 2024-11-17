package com.example.aiquiz.chatgpt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GPTRequest {
    private String model;
    private String prompt;
    private int max_tokens;
    private double temperature;

    public GPTRequest(String prompt) {
        this.model = "gpt-4";
        this.prompt = prompt;
        this.max_tokens = 100;
        this.temperature = 0.7;
    }

}