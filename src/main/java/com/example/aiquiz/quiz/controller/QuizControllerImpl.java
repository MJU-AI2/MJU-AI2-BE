package com.example.aiquiz.quiz.controller;

//import com.example.aiquiz.chatgpt.service.GPTService;
import com.example.aiquiz.quiz.dto.response.GetQuizResponse;
import com.example.aiquiz.quiz.dto.response.PageResponse;
import com.example.aiquiz.util.dto.response.SuccessResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.aiquiz.quiz.service.QuizService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/quiz")
@RequiredArgsConstructor
public class QuizControllerImpl implements QuizController {

	private final QuizService quizService;

	@Override
	@GetMapping("/all")
	public PageResponse<GetQuizResponse> getAllQuiz(
			@RequestParam(value = "size", required = false, defaultValue = "20") int size,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page) {
		PageRequest pageRequest = PageRequest.of( page, size );
		return quizService.getAllQuiz( pageRequest );
	}

	@Override
	public ResponseEntity<SuccessResponse<Integer>> getQuizCount() {
		return SuccessResponse.of( quizService.getQuizCount() ).asHttp( HttpStatus.OK );
	}
}
