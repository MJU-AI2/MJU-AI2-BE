package com.example.aiquiz.quiz.dto.requeset;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema( name = "Submit Answer Request", description = "답안 제출 요청" )
public record SubmitAnswerRequest(
        @Schema( description = "정답 목록" ) List<Answer> answers
) {}
