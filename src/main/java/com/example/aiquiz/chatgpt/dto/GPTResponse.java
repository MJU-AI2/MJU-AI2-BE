package com.example.aiquiz.chatgpt.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GPTResponse {

    private String id;
    private String object;
    private String created;
    private String model;
    private List<Choice> choices;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Choice {
        private Message message;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Message {
            private String content; // 문제 내용
            private String title;   // 문제 제목
            private String type;    // 문제 유형 (객관식: multiple-choice, 주관식: subjective)
        }
    }
}
