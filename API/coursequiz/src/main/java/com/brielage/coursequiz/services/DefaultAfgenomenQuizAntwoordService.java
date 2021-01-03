package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.AfgenomenQuizAntwoord;
import com.brielage.coursequiz.repositories.AfgenomenQuizAntwoordRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultAfgenomenQuizAntwoordService
        implements AfgenomenQuizAntwoordService {
    private final AfgenomenQuizAntwoordRepository afgenomenQuizAntwoordRepository;

    DefaultAfgenomenQuizAntwoordService(AfgenomenQuizAntwoordRepository afgenomenQuizAntwoordRepository) {
        this.afgenomenQuizAntwoordRepository = afgenomenQuizAntwoordRepository;
    }

    @Override
    public List<AfgenomenQuizAntwoord> findAll() {
        return afgenomenQuizAntwoordRepository.findAll();
    }

    @Override
    public Optional<AfgenomenQuizAntwoord> findById(long id) {
        return afgenomenQuizAntwoordRepository.findById(id);
    }
}
