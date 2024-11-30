package com.example.aiquiz.quiz.controller;

import java.io.ByteArrayOutputStream;

import com.example.aiquiz.chatgpt.service.GPTService;
import com.example.aiquiz.common.dto.response.PageResponse;
import com.example.aiquiz.common.dto.response.SuccessResponse;
import com.example.aiquiz.exception.QuizNotFoundException;
import com.example.aiquiz.quiz.constants.*;
import com.example.aiquiz.quiz.dto.requeset.GenerateQuizRequest;
import com.example.aiquiz.quiz.dto.requeset.SubmitAnswerRequest;
import com.example.aiquiz.quiz.dto.response.GetQuizDetailResponse;
import com.example.aiquiz.quiz.dto.response.GetQuizResponse;
import com.example.aiquiz.quiz.dto.response.GetResultResponse;
import com.example.aiquiz.quiz.entity.Quiz;
import com.example.aiquiz.quiz.service.QuizService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/quiz")
@RequiredArgsConstructor
public class QuizControllerImpl implements QuizController {

	private final QuizService quizService;
	private final GPTService gptService;

	@PostMapping("/generate")
	public ResponseEntity<Quiz> generateQuiz(@RequestBody GenerateQuizRequest request) {
		try {
			// 요청 바디에서 데이터를 추출
			String prompt = generatePrompt(request.getCategory(), request.getTopic(), request.getDifficulty(), request.getQuizType());

			// QuizService 호출
			Quiz quiz = quizService.createQuizFromGPT(
					prompt,
					request.getCategory(),
					request.getTopic(),
					request.getDifficulty(),
					request.getQuizType()
			);
			return ResponseEntity.ok(quiz);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}


	/**
	 * 카테고리, 주제, 난이도를 기반으로 GPT에 전달할 프롬프트 생성
	 */
	private String generatePrompt(Category category, String topic, DifficultyLevel difficulty, QuizType quizType) {
		String prompt = category.getPromptText();
		if (category == Category.COMPUTER_SCIENCE_TERM) {
			prompt += Topic.valueOf(topic).getPromptText();
		} else if (category == Category.CODING_ALGORITHM) {
			prompt += AlgorithmLanguage.valueOf(topic).getPromptText();
		}
		prompt += difficulty.getPromptText();
		prompt += quizType.getPromptText();
		return prompt;
	}

	@Override
	public ResponseEntity<byte[]> generateQR(Long quizId) throws Exception {
		String problemUrl = "http://43.201.19.254:5173/" + quizId;

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

	@Override
	public ResponseEntity<SuccessResponse<PageResponse<GetQuizResponse>>> getAllClothes(int size, int page) {
		PageRequest pageRequest = PageRequest.of(page, size);
		return SuccessResponse.of(quizService.getAllQuiz(pageRequest)).asHttp(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SuccessResponse<GetQuizDetailResponse>> getQuizDetail(Long quizID) {
		GetQuizDetailResponse getQuizDetailResponse = quizService.getQuizDetail(quizID);
		return SuccessResponse.of(getQuizDetailResponse).asHttp(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SuccessResponse<Integer>> getQuizCount() {
		return SuccessResponse.of(quizService.getQuizCount()).asHttp(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SuccessResponse<GetResultResponse>> submitAnswer(SubmitAnswerRequest submitAnswerRequest) {
		GetResultResponse getResultResponse = quizService.submitAnswer(submitAnswerRequest);
		return SuccessResponse.of(getResultResponse).asHttp(HttpStatus.OK);
	}
}
