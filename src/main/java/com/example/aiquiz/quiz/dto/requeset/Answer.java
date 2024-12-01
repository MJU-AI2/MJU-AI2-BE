package com.example.aiquiz.quiz.dto.requeset;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema( name = "Answer DTO", description = "n개의 답안을 받기 위한 DTO")
public record Answer(
        @Schema( description = "퀴즈 ID" ) Long quizID,
        @Schema( description = "제출 답안" ) String submitAnswer
) {
}
