package com.example.aiquiz.quiz.dto.response;

import com.example.aiquiz.quiz.entity.Result;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema( name = "Result DTO", description = "n개의 결과 응답을 위한 DTO")
public record Grade(
        @Schema( description = "퀴즈 ID" ) Long quizID,
        @Schema( description = "제출 답안" ) String submitAnswer,
        @Schema( description = "채점 결과" )Result result
        ) {
    public static Grade of ( Long quizID, String submitAnswer, Result result ){
        return Grade.builder()
                .quizID( quizID )
                .submitAnswer( submitAnswer )
                .result( result )
                .build();
    }
}
