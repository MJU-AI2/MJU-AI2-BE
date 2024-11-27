package com.example.aiquiz.quiz.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aiquiz.quiz.entity.Quiz;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

    Page<Quiz> findAllByDeletedAtIsNull(Pageable pageable );
    List<Quiz> findAllByDeletedAtIsNull();
}
