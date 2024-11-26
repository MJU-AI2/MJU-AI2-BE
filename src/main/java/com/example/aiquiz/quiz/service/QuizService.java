package com.example.aiquiz.quiz.service;

import com.example.aiquiz.quiz.dto.response.GetQuizResponse;
import com.example.aiquiz.quiz.dto.response.PageResponse;
import com.example.aiquiz.utils.PageUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.aiquiz.quiz.repository.QuizRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizService {

	private final QuizRepository quizRepository;

	public PageResponse<GetQuizResponse> getAllQuiz(PageRequest pageRequest) {
		return PageUtils.toPageResponse(quizRepository.findAllByDeletedAtIsNull(pageRequest)).map(GetQuizResponse::of);
	}

}
