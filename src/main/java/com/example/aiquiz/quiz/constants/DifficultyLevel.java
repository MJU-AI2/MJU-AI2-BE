package com.example.aiquiz.quiz.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum DifficultyLevel {
    EASY("초급", "초급 - 프로그래밍 입문자인 초등학생"),
    MEDIUM("중급", "중급 - 프로그래밍을 어느정도 접해본 중학생"),
    HARD("고급", "고급 - 프로그래밍에 익숙한 중,고등학생");

    @Getter
    private final String name;
    private final String promptText;

    public String getPromptText() {
        return "문제의 난이도는 " + promptText + "정도의 수준으로 해줘.";
    }

}
