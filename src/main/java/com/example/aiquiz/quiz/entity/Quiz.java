package com.example.aiquiz.quiz.entity;

import com.example.aiquiz.util.BaseEntity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;

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
	private String answer;
	@Enumerated( EnumType.STRING )
	private Category category;
	private String difficulty;
	private LocalDateTime deletedAt;

	@Builder
	public Quiz( Long id, String title, String content, String answer, Category category, String difficulty){
		this.id = id;
		this.title = title;
		this.content = content;
		this.answer = answer;
		this.category = category;
		this.difficulty = difficulty;
		this.deletedAt = null;
	}
}
