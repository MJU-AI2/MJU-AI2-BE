package com.example.aiquiz.quiz.dto.requeset;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema( name="Submit Answer Request", description="답안 제출 요청")
public record SubmitAnswerRequest(
        @Schema( description = "답안 내용", requiredMode = Schema.RequiredMode.REQUIRED ) String answer,
        @Schema( description = "퀴즈 ID", requiredMode = Schema.RequiredMode.REQUIRED ) Long quizID,
        @Schema( description = "소모 시간", requiredMode = Schema.RequiredMode.REQUIRED ) int timeSpent
) {}
