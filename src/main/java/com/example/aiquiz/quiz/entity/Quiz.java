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
	@Column(name = "quiz_num")
	private int quizNum;
	private String title;
	private String content;
	private String answer;
	@Enumerated( EnumType.STRING )
	private Category category;
	private String url;
	private LocalDateTime deletedAt;

	@Builder
	public Quiz( Long id, int quizNum, String title, String content, String answer, Category category, String url ){
		this.id = id;
		this.quizNum = quizNum;
		this.title = title;
		this.content = content;
		this.answer = answer;
		this.category = category;
		this.url = url;
		this.deletedAt = null;
	}
}
