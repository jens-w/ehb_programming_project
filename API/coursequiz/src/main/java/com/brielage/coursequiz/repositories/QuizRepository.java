package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository
        extends JpaRepository<Quiz, Long> {
}
