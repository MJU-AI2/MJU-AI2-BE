package com.example.aiquiz.exception;

public class QuizNotFoundException extends CustomException {
    public QuizNotFoundException() {
        super(QuizErrorCode.QUIZ_NOT_FOUND);
    }
}
