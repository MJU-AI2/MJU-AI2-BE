package com.example.aiquiz.quiz.controller;

//import com.example.aiquiz.chatgpt.service.GPTService;
import com.example.aiquiz.quiz.dto.response.GetQuizResponse;
import com.example.aiquiz.quiz.entity.Category;
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
	public ResponseEntity<GetQuizResponse> getAllQuiz(int size, int page) {
		return null;
	}
}
