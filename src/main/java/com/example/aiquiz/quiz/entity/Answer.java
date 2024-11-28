package com.example.aiquiz.quiz.entity;

import com.example.aiquiz.util.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@FieldNameConstants
@NoArgsConstructor( access= AccessLevel.PROTECTED )
public class Answer extends BaseEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    private String answer;
    @CreatedDate
    private LocalDateTime submittedTime;
    private int timeSpent;
    private LocalDateTime deletedAt;

    @Builder
    public Answer( Long id, String answer, LocalDateTime submittedTime, int timeSpent ) {
        this.id = id;
        this.answer = answer;
        this.submittedTime = submittedTime;
        this.timeSpent = timeSpent;
        this.deletedAt = null;
    }
}
