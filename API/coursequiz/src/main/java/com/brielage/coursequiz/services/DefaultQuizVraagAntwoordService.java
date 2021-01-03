package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.QuizVraagAntwoord;
import com.brielage.coursequiz.repositories.QuizVraagAntwoordRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultQuizVraagAntwoordService
        implements QuizVraagAntwoordService {
    private final QuizVraagAntwoordRepository quizVraagAntwoordRepository;

    DefaultQuizVraagAntwoordService(QuizVraagAntwoordRepository quizVraagAntwoordRepository) {
        this.quizVraagAntwoordRepository = quizVraagAntwoordRepository;
    }

    @Override
    public List<QuizVraagAntwoord> findAll() {
        return quizVraagAntwoordRepository.findAll();
    }

    @Override
    public Optional<QuizVraagAntwoord> findById(long id) {
        return quizVraagAntwoordRepository.findById(id);
    }
}
