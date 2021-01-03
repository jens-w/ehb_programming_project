package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.QuizVraag;

import java.util.List;
import java.util.Optional;

public interface QuizVraagService {
    List<QuizVraag> findAll();

    Optional<QuizVraag> findById(long id);
}
