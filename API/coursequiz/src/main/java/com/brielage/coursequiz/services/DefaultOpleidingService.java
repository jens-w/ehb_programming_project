package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.Opleiding;
import com.brielage.coursequiz.repositories.OpleidingRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultOpleidingService
        implements OpleidingService {
    private final OpleidingRepository opleidingRepository;

    DefaultOpleidingService(OpleidingRepository opleidingRepository) {
        this.opleidingRepository = opleidingRepository;
    }

    @Override
    public List<Opleiding> findAll() {
        return opleidingRepository.findAll();
    }

    @Override
    public Optional<Opleiding> findById(long id) {
        return opleidingRepository.findById(id);
    }

    @Override
    public void create(Opleiding opleiding) {
        opleidingRepository.create(opleiding);
    }
}
