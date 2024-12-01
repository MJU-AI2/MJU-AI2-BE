package com.example.aiquiz.quiz.dto.response;

import com.example.aiquiz.quiz.entity.Result;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
@Schema( name = "Get Result Response", description = "채점 결과 응답" )
public record GetResultResponse(
        @Schema( description = "결과 목록" ) List<Grade> results
) {
    public static GetResultResponse of( List<Grade> grades ) {
        return GetResultResponse.builder()
                .results( grades )
                .build();
    }
}
