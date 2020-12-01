package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.QuizVraag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizVraagRepository
        extends JpaRepository<QuizVraag, Long> {
}
