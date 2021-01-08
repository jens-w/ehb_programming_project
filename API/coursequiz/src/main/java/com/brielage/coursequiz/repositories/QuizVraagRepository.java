package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.QuizVraag;

import java.util.List;

public interface QuizVraagRepository {
    void create(QuizVraag quizVraag);

    List<QuizVraag> findByQuizId(long quizId);
}
