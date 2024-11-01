package com.example.aiquiz.quiz.service;

import org.springframework.stereotype.Service;

import com.example.aiquiz.quiz.repository.QuizRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizService {

	private final QuizRepository quizRepository;

}
