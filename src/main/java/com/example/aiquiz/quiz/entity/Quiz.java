package com.example.aiquiz.quiz.entity;

import com.example.aiquiz.quiz.constants.Category;
import com.example.aiquiz.quiz.constants.QuizType;
import com.example.aiquiz.util.BaseEntity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@FieldNameConstants
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Quiz extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;      // 문제 제목
	private String content;    // 문제 내용

	@ElementCollection
	@CollectionTable(name = "quiz_choices", joinColumns = @JoinColumn(name = "quiz_id"))
	@Column(name = "choice")
	private List<String> choices; // 보기
	private String answer;     // 정답

	@Enumerated(EnumType.STRING)
	private Category category; // 카테고리

	private String difficulty; // 난이도

	@Enumerated(EnumType.STRING)
	private QuizType quizType; // 퀴즈 유형

	private LocalDateTime deletedAt;

	@Builder
	public Quiz(Long id, String title, String content, List<String> choices, String answer, Category category, String difficulty, QuizType quizType) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.choices = choices;
		this.answer = answer;
		this.category = category;
		this.difficulty = difficulty;
		this.quizType = quizType;
		this.deletedAt = null;
	}
}
