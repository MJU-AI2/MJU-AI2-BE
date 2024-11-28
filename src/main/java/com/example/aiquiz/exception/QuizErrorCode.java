package com.example.aiquiz.exception;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum QuizErrorCode implements ErrorCode {

    QUIZ_NOT_FOUND(404, "CT001", "해당 퀴즈를 찾을 수 없습니다."),
    QUIZ_DUPLICATE(405, "CT002", "이미 존재하는 퀴즈입니다.");

    private final int status;
    private final String code;
    private final String message;

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
