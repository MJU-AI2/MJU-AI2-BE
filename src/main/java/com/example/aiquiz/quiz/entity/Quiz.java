package com.example.aiquiz.quiz.entity;

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
	private String title;
	private String content;
	private String difficulty;
	private String hints;
	private String answer;
	@Enumerated(EnumType.STRING)
	private Category category;
	private String imageURL;
	private int timeLimit;
	private String url;
	private LocalDateTime deletedAt;

	@Builder
	public Quiz(Long id, String title, String content, String difficulty, String hints, String answer, Category category,
				String imageURL, int timeLimit, String url ) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.difficulty = difficulty;
		this.hints = hints;
		this.answer = answer;
		this.category = category;
		this.imageURL = imageURL;
		this.timeLimit = timeLimit;
		this.url = url;
		this.deletedAt = null;
	}

}
