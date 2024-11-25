package com.example.aiquiz.quiz.dto.response;

import com.example.aiquiz.quiz.entity.Quiz;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema( name="Quiz Response", description="퀴즈 정보 응답")
public record GetQuizResponse(
        @Schema( description="퀴즈 id")Long id,
        @Schema( description="퀴즈 제목") String title,
        @Schema( description="퀴즈 내용") String content,
        @Schema( description="퀴즈 난이도") String difficulty,
        @Schema( description="퀴즈 힌트") String hints,
        @Schema( description="제한 시간") int timeLimit,
        @Schema( description="퀴즈 정답") String answer,
        @Schema( description="퀴즈 이미지 URL") String imageURL ) {
    public static GetQuizResponse of(Quiz quiz){
        return GetQuizResponse.builder()
                .id( quiz.getId() )
                .title( quiz.getTitle() )
                .content( quiz.getContent() )
                .difficulty( quiz.getDifficulty() )
                .hints( quiz.getHints() )
                .timeLimit( quiz.getTimeLimit() )
                .answer( quiz.getAnswer() )
                .imageURL( quiz.getImageURL() )
                .build();
    }
}
