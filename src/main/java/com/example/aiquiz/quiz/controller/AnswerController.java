package com.example.aiquiz.quiz.controller;

import com.example.aiquiz.common.dto.response.SuccessResponse;
import com.example.aiquiz.quiz.dto.requeset.SubmitAnswerRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Tag(name = "Answer API", description = "정답 제출 관련 API")
public interface AnswerController {

    @Operation(summary = "답안 등록", description = "답안 등록을 위한 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "성공적으로 상품 등록 완료")
    })
    @PostMapping(value = "/submitAnswer", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<SuccessResponse<Void>> submitAnswer(
            @Valid @ModelAttribute SubmitAnswerRequest submitAnswerRequest );
}
