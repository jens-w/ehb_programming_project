package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.QuizVraagAntwoord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizVraagAntwoordRepository
        extends JpaRepository<QuizVraagAntwoord, Long> {
}
