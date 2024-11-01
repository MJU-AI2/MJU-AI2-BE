package com.example.aiquiz.quiz.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Category {

	// Category 예시
	JAVA("자바"), C("C언어");

	private final String name;
}
