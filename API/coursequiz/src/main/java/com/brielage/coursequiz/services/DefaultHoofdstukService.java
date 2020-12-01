package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.Hoofdstuk;
import com.brielage.coursequiz.repositories.HoofdstukRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultHoofdstukService
        implements HoofdstukService {
    private final HoofdstukRepository hoofdstukRepository;

    DefaultHoofdstukService(HoofdstukRepository hoofdstukRepository) {
        this.hoofdstukRepository = hoofdstukRepository;
    }

    @Override
    public List<Hoofdstuk> findAll() {
        return hoofdstukRepository.findAll();
    }

    @Override
    public Optional<Hoofdstuk> findById(long id) {
        return hoofdstukRepository.findById(id);
    }
}
