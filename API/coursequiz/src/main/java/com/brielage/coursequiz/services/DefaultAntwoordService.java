package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.Antwoord;
import com.brielage.coursequiz.repositories.AntwoordRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultAntwoordService
        implements AntwoordService {
    private final AntwoordRepository antwoordRepository;

    DefaultAntwoordService(AntwoordRepository antwoordRepository) {
        this.antwoordRepository = antwoordRepository;
    }

    @Override
    public List<Antwoord> findAll() {
        return antwoordRepository.findAll();
    }

    @Override
    public Optional<Antwoord> findById(long id) {
        return antwoordRepository.findById(id);
    }
}
