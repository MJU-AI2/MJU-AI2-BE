package com.example.aiquiz.quiz.service;

import com.example.aiquiz.chatgpt.dto.GPTResponse;
import com.example.aiquiz.chatgpt.service.GPTService;
import com.example.aiquiz.common.dto.response.PageResponse;
import com.example.aiquiz.exception.QuizNotFoundException;
import com.example.aiquiz.quiz.constants.Category;
import com.example.aiquiz.quiz.constants.DifficultyLevel;
import com.example.aiquiz.quiz.constants.QuizType;
import com.example.aiquiz.quiz.dto.requeset.SubmitAnswerRequest;
import com.example.aiquiz.quiz.dto.response.GetQuizDetailResponse;
import com.example.aiquiz.quiz.dto.response.GetQuizResponse;
import com.example.aiquiz.quiz.dto.response.GetResultResponse;
import com.example.aiquiz.quiz.entity.Quiz;
import com.example.aiquiz.quiz.entity.Result;
import com.example.aiquiz.quiz.repository.QuizRepository;
import com.example.aiquiz.util.PageUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {

	private final QuizRepository quizRepository;
	private final GPTService gptService;

	public PageResponse<GetQuizResponse> getAllQuiz(PageRequest pageRequest) {
		return PageUtils.toPageResponse(quizRepository.findAllByDeletedAtIsNull(pageRequest))
				.map(GetQuizResponse::of);
	}

	public GetQuizDetailResponse getQuizDetail(Long quizID) {
		Quiz quiz = quizRepository.findByIdAndDeletedAtIsNull(quizID)
				.orElseThrow(QuizNotFoundException::new);
		return GetQuizDetailResponse.of(quiz);
	}

	public GetResultResponse submitAnswer(SubmitAnswerRequest submitAnswerRequest) {
		Quiz quiz = quizRepository.findByIdAndDeletedAtIsNull(submitAnswerRequest.quizID())
				.orElseThrow(QuizNotFoundException::new);
		Result result;
		if (quiz.getAnswer().equals(submitAnswerRequest.submitAnswer())) result = Result.SUCCESS;
		else result = Result.WRONG;
		return GetResultResponse.of(quiz.getId(), submitAnswerRequest.submitAnswer(), result);
	}

	public int getQuizCount() {
		return quizRepository.findAllByDeletedAtIsNull().size();
	}

	@Transactional
	public Quiz createQuizFromGPT(String prompt, Category category, String topic, DifficultyLevel difficulty, QuizType quizType) throws Exception {
		// GPT에서 문제 생성 및 파싱된 데이터 수신
		GPTResponse gptResponse = gptService.generateQuiz(prompt, quizType);

		String question = gptResponse.getQuestion();     // 문제
		List<String> choices = gptResponse.getChoices(); // 보기

		Quiz quiz = Quiz.builder()
				.title(gptResponse.getTitle())
				.content(question)
				.choices(choices)
				.answer(gptResponse.getAnswer())
				.category(category)
				.difficulty(difficulty.name())
				.quizType(quizType)
				.build();

		return quizRepository.save(quiz);
	}






}
