package com.example.aiquiz.quiz.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Result {
    SUCCESS( "정답" ),
    WRONG( "오답" );

    private final String message;
}
