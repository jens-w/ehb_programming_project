package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.QuizVraag;
import com.brielage.coursequiz.repositories.QuizVraagRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultQuizVraagService
        implements QuizVraagService {
    private final QuizVraagRepository quizVraagRepository;

    DefaultQuizVraagService(QuizVraagRepository quizVraagRepository) {
        this.quizVraagRepository = quizVraagRepository;
    }

    @Override
    public List<QuizVraag> findAll() {
        return quizVraagRepository.findAll();
    }

    @Override
    public Optional<QuizVraag> findById(long id) {
        return quizVraagRepository.findById(id);
    }
}
