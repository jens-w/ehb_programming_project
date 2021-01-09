package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.QuizVraag;
import com.brielage.coursequiz.repositories.QuizVraagRepository;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DefaultQuizVraagService
        implements QuizVraagService {
    private final QuizVraagRepository quizVraagRepository;

    DefaultQuizVraagService(QuizVraagRepository quizVraagRepository) {
        this.quizVraagRepository = quizVraagRepository;
    }

    @Override
    public void create(QuizVraag quizVraag) {
        quizVraagRepository.create(quizVraag);
    }

    @Override
    public List<QuizVraag> findByQuizId(long quizId) {
        return quizVraagRepository.findByQuizId(quizId);
    }
}
