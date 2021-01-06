package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.Quiz;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaQuizRepository
        implements QuizRepository {
    private final EntityManager manager;

    public JpaQuizRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void create(Quiz quiz) {
        manager.persist(quiz);
    }

    @Override
    public List<Quiz> findAll() {
        return manager.createQuery("select q from Quiz q order by q.id",
                Quiz.class).getResultList();
    }

    @Override
    public Optional<Quiz> findById(long id) {
        return Optional.ofNullable(manager.find(Quiz.class, id));
    }

    @Override
    public List<Quiz> findByNaam(String naam) {
        return manager.createQuery(
                "select q from Quiz q " +
                        "where q.naam = :naam",
                Quiz.class)
                .setParameter("naam", naam)
                .getResultList();
    }
}
