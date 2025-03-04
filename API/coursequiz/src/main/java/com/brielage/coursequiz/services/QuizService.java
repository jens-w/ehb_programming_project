package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.Quiz;

import java.util.List;
import java.util.Optional;

public interface QuizService {
    void create(Quiz quiz);

    List<Quiz> findAll();

    Optional<Quiz> findById(long id);

    List<Quiz> findByNaam(String naam);

    List<Quiz> findByVakId(long vakId);
}
