package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.Quiz;
import com.brielage.coursequiz.repositories.QuizRepository;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultQuizService
        implements QuizService {
    private final QuizRepository quizRepository;

    DefaultQuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public void create(Quiz quiz) {
        quizRepository.create(quiz);
    }

    @Override
    public List<Quiz> findAll() {
        return quizRepository.findAll();
    }

    @Override
    public Optional<Quiz> findById(long id) {
        return quizRepository.findById(id);
    }

    @Override
    public List<Quiz> findByNaam(String naam) {
        return quizRepository.findByNaam(naam);
    }

    @Override
    public List<Quiz> findByVakId(long vakId) {
        return quizRepository.findByVakId(vakId);
    }
}
