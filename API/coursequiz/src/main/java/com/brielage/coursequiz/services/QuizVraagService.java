package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.QuizVraag;

import java.util.List;

public interface QuizVraagService {
    void create(QuizVraag quizVraag);

    List<QuizVraag> findByQuizId(long quizId);
}
