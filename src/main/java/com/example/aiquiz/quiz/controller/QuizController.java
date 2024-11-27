package com.example.aiquiz.quiz.controller;

import com.example.aiquiz.common.dto.response.PageResponse;
import com.example.aiquiz.common.dto.response.SuccessResponse;
import com.example.aiquiz.quiz.dto.response.GetQuizResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.aiquiz.chatgpt.service.GPTService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Quiz API", description = "문제 관련 API")
public interface QuizController {

	@Operation(summary = "QR 생성 API", description = "문제 조회를 위한 QR 생성 API")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 생성 완료")
	})
	@GetMapping(value = "/QR/{quizId}")
	ResponseEntity<byte[]> generateQR(@PathVariable Long quizId) throws Exception;

	@Operation(summary = "퀴즈 전체 조회", description = "Page에 맞게 퀴즈 조회")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200"
					, description = "성공적으로 조회")
	})
	@GetMapping("/all")
	ResponseEntity<SuccessResponse<PageResponse<GetQuizResponse>>> getAllClothes(
			@RequestParam(value = "size", required = false, defaultValue = "20") int size,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page);

	@Operation( summary = "퀴즈 개수 조회", description = "테스트를 위한 퀴즈 개수 조회" )
	@ApiResponses( value = {
			@ApiResponse( responseCode = "200", description = "성공적으로 조회" )
	})
	@GetMapping( "/getQuizCount" )
	ResponseEntity<SuccessResponse<Integer>> getQuizCount();
}
