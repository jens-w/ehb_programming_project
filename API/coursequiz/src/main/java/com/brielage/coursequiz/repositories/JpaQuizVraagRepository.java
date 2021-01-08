package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.QuizVraag;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class JpaQuizVraagRepository
implements QuizVraagRepository{
    private final EntityManager manager;

    public JpaQuizVraagRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void create(QuizVraag quizVraag) {
        manager.persist(quizVraag);
    }

    @Override
    public List<QuizVraag> findByQuizId(long quizId) {
        return null;
    }
}
