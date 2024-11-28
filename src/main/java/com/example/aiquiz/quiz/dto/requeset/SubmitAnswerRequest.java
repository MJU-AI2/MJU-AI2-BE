package com.example.aiquiz.quiz.dto.requeset;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema( name = "Submit Answer Request", description = "답안 제출 요청" )
public record SubmitAnswerRequest(
        @Schema( description = "퀴즈 ID" ) Long quizID,
        @Schema( description = "제출 답안" ) String submitAnswer
) {}
