package com.example.aiquiz.quiz.service;

import com.example.aiquiz.common.dto.response.PageResponse;
import com.example.aiquiz.exception.QuizNotFoundException;
import com.example.aiquiz.quiz.dto.response.GetQuizDetailResponse;
import com.example.aiquiz.quiz.dto.response.GetQuizResponse;
import com.example.aiquiz.quiz.entity.Quiz;
import com.example.aiquiz.util.PageUtils;
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
				.map( quiz -> {
					return GetQuizResponse.of( quiz );
				});
	}

	public GetQuizDetailResponse getQuizDetail(Long quizID) {
		Quiz quiz = quizRepository.findByIdAndDeletedAtIsNull(quizID)
				.orElseThrow(QuizNotFoundException::new);
		return GetQuizDetailResponse.of( quiz );
	}

	public int getQuizCount(){
		return quizRepository.findAllByDeletedAtIsNull().size();
	}

}
