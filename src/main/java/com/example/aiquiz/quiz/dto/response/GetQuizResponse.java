package com.example.aiquiz.quiz.dto.response;

import java.util.List;

import com.example.aiquiz.quiz.constants.Category;
import com.example.aiquiz.quiz.constants.QuizType;
import com.example.aiquiz.quiz.entity.Quiz;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema( name="Get Quiz Response", description="퀴즈 조회 응답")
public record GetQuizResponse(
        @Schema( description = "퀴즈 ID" ) Long id,
        @Schema( description = "퀴즈 제목" ) String title,
        @Schema( description = "퀴즈 내용" ) String content,
        @Schema( description = "퀴즈 정답" ) String answer,
        @Schema( description = "퀴즈 카테고리" ) Category category,
        @Schema( description = "퀴즈 난이도" ) String difficulty,
        @Schema( description = "문제 유형" ) QuizType quizType,
        @Schema( description = "객관식 선택지" ) List<String> choices
        ) {
    public static GetQuizResponse of( Quiz quiz ){
        return GetQuizResponse.builder()
                .id( quiz.getId() )
                .title( quiz.getTitle() )
                .content( quiz.getContent() )
                .answer( quiz.getAnswer() )
                .category( quiz.getCategory() )
                .difficulty( quiz.getDifficulty())
                .quizType( quiz.getQuizType() )
                .choices(quiz.getChoices())
                .build();
    }
}
