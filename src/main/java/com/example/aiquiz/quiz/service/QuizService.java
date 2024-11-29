package com.example.aiquiz.quiz.service;

import com.example.aiquiz.common.dto.response.PageResponse;
import com.example.aiquiz.exception.QuizNotFoundException;
import com.example.aiquiz.quiz.dto.requeset.SubmitAnswerRequest;
import com.example.aiquiz.quiz.dto.response.GetQuizDetailResponse;
import com.example.aiquiz.quiz.dto.response.GetQuizResponse;
import com.example.aiquiz.quiz.dto.response.GetResultResponse;
import com.example.aiquiz.quiz.entity.Quiz;
import com.example.aiquiz.quiz.entity.Result;
import com.example.aiquiz.util.PageUtils;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.aiquiz.quiz.repository.QuizRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizService {

	private final QuizRepository quizRepository;

	public PageResponse<GetQuizResponse> getAllQuiz(PageRequest pageRequest ){
		return PageUtils.toPageResponse( quizRepository.findAllByDeletedAtIsNull( pageRequest ))
				.map(GetQuizResponse::of);
	}

	public GetQuizDetailResponse getQuizDetail(Long quizID) {
		Quiz quiz = quizRepository.findByIdAndDeletedAtIsNull(quizID)
				.orElseThrow(QuizNotFoundException::new);
		return GetQuizDetailResponse.of( quiz );
	}

	public GetResultResponse submitAnswer( SubmitAnswerRequest submitAnswerRequest ){
		Quiz quiz = quizRepository.findByIdAndDeletedAtIsNull( submitAnswerRequest.quizID() ).orElseThrow(QuizNotFoundException::new);
		Result result;
		if( quiz.getAnswer().equals( submitAnswerRequest.submitAnswer() ) ) result = Result.SUCCESS;
		else result = Result.WRONG;
		return GetResultResponse.of( quiz.getId(), submitAnswerRequest.submitAnswer(), result );
	}

	public int getQuizCount(){
		return quizRepository.findAllByDeletedAtIsNull().size();
	}

}
