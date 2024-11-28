package com.example.aiquiz.quiz.service;

import com.example.aiquiz.quiz.dto.requeset.SubmitAnswerRequest;
import com.example.aiquiz.quiz.entity.Answer;
import com.example.aiquiz.quiz.repository.AnswerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private AnswerRepository answerRepository;

    @Transactional
    public void submitAnswer(SubmitAnswerRequest submitAnswerRequest ) {
        Answer answer = Answer.builder()
                .answer( submitAnswerRequest.answer() )
                .timeSpent(submitAnswerRequest.timeSpent() )
                .build();
        answerRepository.save(answer);
    }
}
