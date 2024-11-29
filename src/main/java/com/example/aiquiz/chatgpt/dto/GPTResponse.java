package com.example.aiquiz.chatgpt.dto;

import lombok.Data;
import java.util.List;

@Data
public class GPTResponse {
    private String title; // 문제 제목
    private String question; // 문제 내용
    private List<String> choices; // 객관식 선택지
    private String answer; // 정답
}
