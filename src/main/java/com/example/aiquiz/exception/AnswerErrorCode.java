package com.example.aiquiz.exception;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum AnswerErrorCode implements ErrorCode {

    ANSWER_NOT_FOUND(404, "CT001", "해당 답안을 찾을 수 없습니다.");

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
