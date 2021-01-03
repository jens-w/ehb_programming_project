package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.AfgenomenQuiz;

import java.util.List;
import java.util.Optional;

public interface AfgenomenQuizService {
    List<AfgenomenQuiz> findAll();

    Optional<AfgenomenQuiz> findById(long id);
}
