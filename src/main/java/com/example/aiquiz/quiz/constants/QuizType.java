package com.example.aiquiz.quiz.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum QuizType {
    MULTIPLE_CHOICE("객관식", "중복답안이 없는 4지선다 형태로 문제와 4개의 보기를 "),
    SUBJECTIVE("주관식", "주관식 형태로 ");

    @Getter
    private final String name;
    private final String promptText;

    public String getPromptText() {
        return "문제의 형태는 " + promptText + "제공해줘.";
    }
}
