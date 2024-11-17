package com.example.aiquiz.quiz.controller;

import com.example.aiquiz.chatgpt.service.GPTService;
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
	private final GPTService gptService;


	/**
	 *
	 * @param topic QuizTopic or Language
	 * @return generated quiz by gpt
	 */
	@GetMapping("/generate")
	public ResponseEntity<String> generateQuiz(@RequestParam String topic) {
		try {
			String prompt = "Provide a coding quiz question about " + topic + ".";
			String quiz = gptService.generateQuiz(prompt);
			return ResponseEntity.ok(quiz);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error generating quiz: " + e.getMessage());
		}
	}
}
