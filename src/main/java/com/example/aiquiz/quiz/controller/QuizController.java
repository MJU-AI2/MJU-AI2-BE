package com.example.aiquiz.quiz.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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


}
