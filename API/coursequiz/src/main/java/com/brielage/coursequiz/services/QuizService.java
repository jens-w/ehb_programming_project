package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.Quiz;

import java.util.List;
import java.util.Optional;

public interface QuizService {
    List<Quiz> findAll();

    Optional<Quiz> findById(long id);
}
