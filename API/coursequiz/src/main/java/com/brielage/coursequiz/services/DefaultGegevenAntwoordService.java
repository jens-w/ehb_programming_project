package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.GegevenAntwoord;
import com.brielage.coursequiz.repositories.GegevenAntwoordRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultGegevenAntwoordService
        implements GegevenAntwoordService {
    private final GegevenAntwoordRepository gegevenAntwoordRepository;

    DefaultGegevenAntwoordService(GegevenAntwoordRepository gegevenAntwoordRepository) {
        this.gegevenAntwoordRepository = gegevenAntwoordRepository;
    }

    @Override
    public List<GegevenAntwoord> findAll() {
        return gegevenAntwoordRepository.findAll();
    }

    @Override
    public Optional<GegevenAntwoord> findById(long id) {
        return gegevenAntwoordRepository.findById(id);
    }
}
