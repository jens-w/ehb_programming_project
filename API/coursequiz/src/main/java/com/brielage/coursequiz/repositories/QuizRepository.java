package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.Quiz;

import java.util.List;
import java.util.Optional;

public interface QuizRepository{
    void create(Quiz quiz);

    List<Quiz> findAll();

    Optional<Quiz> findById(long id);

    List<Quiz> findByNaam(String naam);

}
