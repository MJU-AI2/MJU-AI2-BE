package com.example.aiquiz.quiz.dto.requeset;

import com.example.aiquiz.quiz.constants.Category;
import com.example.aiquiz.quiz.constants.DifficultyLevel;
import com.example.aiquiz.quiz.constants.QuizType;

import lombok.Data;

@Data
public class GenerateQuizRequest {
    private Category category;       // 퀴즈 카테고리
    private String topic;            // 주제
    private DifficultyLevel difficulty; // 난이도
    private QuizType quizType;       // 퀴즈 유형
}
