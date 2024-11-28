package com.example.aiquiz.quiz.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Category {

	COMPUTER_SCIENCE_TERM("컴퓨터과학 관련 단어퀴즈", new String[]{"데이터 구조", "알고리즘", "운영 체제", "네트워크"}, new String[]{"초급", "중급", "고급"}),
	CODING_LANGUAGE("코딩 퀴즈", new String[]{"자바", "C", "파이썬", "자바스크립트"}, new String[]{"초급", "중급", "고급"});

	private final String name;
	private final String[] topics;
	private final String[] difficultyLevels;


}
