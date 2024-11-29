package com.example.aiquiz.quiz.dto.response;

import com.example.aiquiz.quiz.constants.Category;
import com.example.aiquiz.quiz.constants.QuizType;
import com.example.aiquiz.quiz.entity.Quiz;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema( name="Quiz Detail Response", description="특정 퀴즈 상세 조회 응답")
public record GetQuizDetailResponse(
        @Schema( description="퀴즈 ID" ) Long quizID,
        @Schema( description="퀴즈 제목" ) String title,
        @Schema( description="퀴즈 내용" ) String content,
        @Schema( description="퀴즈 정답" ) String answer,
        @Schema( description="카테고리" ) Category category,
        @Schema( description="퀴즈 난이도" ) String difficulty,
        @Schema( description = "문제 유형" ) QuizType quizType
) {
    public static GetQuizDetailResponse of( Quiz quiz ){
        return GetQuizDetailResponse.builder()
                .quizID( quiz.getId() )
                .title( quiz.getTitle() )
                .content( quiz.getContent() )
                .answer( quiz.getAnswer() )
                .category( quiz.getCategory() )
                .difficulty( quiz.getDifficulty() )
                .quizType( quiz.getQuizType() )
                .build();
    }
}
