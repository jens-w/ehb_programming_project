package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.QuizVraagAntwoord;

import java.util.List;
import java.util.Optional;

public interface QuizVraagAntwoordService {
    List<QuizVraagAntwoord> findAll();

    Optional<QuizVraagAntwoord> findById(long id);
}
