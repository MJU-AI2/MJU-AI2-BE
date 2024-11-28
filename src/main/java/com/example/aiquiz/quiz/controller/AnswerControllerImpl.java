package com.example.aiquiz.quiz.controller;

import com.example.aiquiz.common.dto.response.SuccessResponse;
import com.example.aiquiz.quiz.dto.requeset.SubmitAnswerRequest;
import com.example.aiquiz.quiz.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/answer")
@RequiredArgsConstructor
public class AnswerControllerImpl implements AnswerController {
    private final AnswerService answerService;

    @Override
    public ResponseEntity<SuccessResponse<Void>> submitAnswer(SubmitAnswerRequest submitAnswerRequest) {
        answerService.submitAnswer(submitAnswerRequest);
        return SuccessResponse.ofNoData().asHttp( HttpStatus.OK );
    }
}
