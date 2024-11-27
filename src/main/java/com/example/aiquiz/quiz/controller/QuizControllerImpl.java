package com.example.aiquiz.quiz.controller;

import java.io.ByteArrayOutputStream;

import com.example.aiquiz.chatgpt.service.GPTService;
import com.example.aiquiz.quiz.entity.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.aiquiz.quiz.service.QuizService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/quiz")
@RequiredArgsConstructor
public class QuizControllerImpl implements QuizController {

	private final QuizService quizService;
	private final GPTService gptService;

	/**
	 * 퀴즈 생성
	 * @param category 퀴즈 카테고리 (컴퓨터과학 단어퀴즈, 코딩)
	 * @param topic 선택된 주제
	 * @param difficulty 난이도 (초급, 중급, 고급)
	 * @return 생성된 퀴즈
	 */
	@GetMapping("/generate")
	public ResponseEntity<String> generateQuiz(
			@RequestParam Category category,
			@RequestParam String topic,
			@RequestParam String difficulty) {
		try {
			// 주어진 카테고리에 맞는 퀴즈 생성 프롬프트 작성
			String prompt = generatePrompt(category, topic, difficulty);
			String quiz = gptService.generateQuiz(prompt);
			return ResponseEntity.ok(quiz);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error generating quiz: " + e.getMessage());
		}
	}

	/**
	 * 주어진 카테고리, 주제, 난이도에 맞는 퀴즈 생성 프롬프트 작성
	 * @param category 카테고리
	 * @param topic 주제
	 * @param difficulty 난이도
	 * @return GPT에 전달할 퀴즈 생성 프롬프트
	 */
	private String generatePrompt(Category category, String topic, String difficulty) {
		String prompt = "";
		if (category == Category.COMPUTER_SCIENCE_TERM) {
			prompt = "Provide a computer science related quiz question about " + topic + " at " + difficulty + " level.";
		} else if (category == Category.CODING_LANGUAGE) {
			prompt = "Provide a coding quiz question about " + topic + " at " + difficulty + " level.";
		}
		return prompt;
	}

	@Override
	public ResponseEntity<byte[]> generateQR(Long quizId) throws Exception {
		// TODO quizURL 변경 필요 - 배포 이후
		String problemUrl = "http://your-domain.com/problem/" + quizId;

		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(
			problemUrl,
			BarcodeFormat.QR_CODE,
			300,
			300
		);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);

		return ResponseEntity.ok()
			.contentType(MediaType.IMAGE_PNG)
			.body(outputStream.toByteArray());
	}
}
