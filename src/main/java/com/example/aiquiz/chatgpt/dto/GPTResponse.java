package com.example.aiquiz.chatgpt.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GPTResponse {
    private String id;
    private String object;
    private String created;
    private String model;
    private List<Choice> choices;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Choice {
        private Message message;
        private String finish_reason;
        private int index;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Message {
        private String role;
        private String content;
    }

    // OpenAI 응답에서 첫 번째 결과를 Quiz 객체로 변환
    public Quiz toQuiz() throws Exception {
        if (choices != null && !choices.isEmpty()) {
            String content = choices.get(0).getMessage().getContent(); // JSON 형식의 응답
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(content, Quiz.class); // 응답을 Quiz 객체로 변환
        }
        throw new Exception("Invalid response: No choices available");
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Quiz {
        private String question;      // 문제 내용
        private List<String> choices; // 선택지 (4지선다의 경우, 아니면 null)
        private String answer;        // 정답
    }
}
