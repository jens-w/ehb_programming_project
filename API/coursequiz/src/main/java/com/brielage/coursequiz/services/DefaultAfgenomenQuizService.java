package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.AfgenomenQuiz;
import com.brielage.coursequiz.repositories.AfgenomenQuizRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultAfgenomenQuizService
        implements AfgenomenQuizService {
    private final AfgenomenQuizRepository afgenomenQuizRepository;

    DefaultAfgenomenQuizService(AfgenomenQuizRepository afgenomenQuizRepository) {
        this.afgenomenQuizRepository = afgenomenQuizRepository;
    }

    @Override
    public List<AfgenomenQuiz> findAll() {
        return afgenomenQuizRepository.findAll();
    }

    @Override
    public Optional<AfgenomenQuiz> findById(long id) {
        return afgenomenQuizRepository.findById(id);
    }
}
