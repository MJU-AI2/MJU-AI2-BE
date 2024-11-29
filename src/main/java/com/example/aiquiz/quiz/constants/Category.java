package com.example.aiquiz.quiz.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {

	COMPUTER_SCIENCE_TERM("컴퓨터과학 단어퀴즈", "컴퓨터과학 관련 단어퀴즈를 제공해줘."),
	CODING_ALGORITHM("코딩", "알고리즘 문제를 빈칸 채우기 문제의 형태로 제공해줘. 어떠한 알고리즘인지 간단히 설명하고 코드중 일부분을 공백으로 바꾸어서 채워 넣을 수 있도록 제공해줘.");

	private final String name;
	private final String promptText;
}
