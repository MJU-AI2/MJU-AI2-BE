package com.example.aiquiz.quiz.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AlgorithmLanguage {
    JAVA("자바"),
    PYTHON("파이썬"),
    C("C"),
    JAVASCRIPT("자바스크립트");

    private final String name;
    public String getPromptText() {
        return "언어는 " + name + "로 정해.";
    }
}
