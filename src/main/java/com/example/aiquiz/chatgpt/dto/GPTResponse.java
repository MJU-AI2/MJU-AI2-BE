package com.example.aiquiz.chatgpt.dto;

import lombok.*;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GPTResponse {
    private String id;
    private String object;
    private String created;
    private Map<String, Object>[] choices;

    public String getGeneratedText() {
        return choices[0].get("text").toString();
    }

}