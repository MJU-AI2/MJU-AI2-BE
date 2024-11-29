package com.example.aiquiz.quiz.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Topic {
    DATA_STRUCTURE("데이터 구조"),
    ALGORITHM("알고리즘"),
    OS("운영체제"),
    NETWORK("네트워크");

    private final String name;
    public String getPromptText() {
        return "퀴즈 주제는 " + name + "로 정해.";
    }
}
