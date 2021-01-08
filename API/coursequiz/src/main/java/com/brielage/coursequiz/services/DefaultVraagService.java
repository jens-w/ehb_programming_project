package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.Vraag;
import com.brielage.coursequiz.repositories.VraagRepository;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultVraagService
        implements VraagService {
    private final VraagRepository vraagRepository;

    DefaultVraagService(VraagRepository vraagRepository) {
        this.vraagRepository = vraagRepository;
    }

    @Override
    public void create(Vraag vraag) {
        vraagRepository.create(vraag);
    }

    @Override
    public List<Vraag> findAll() {
        return vraagRepository.findAll();
    }

    @Override
    public Optional<Vraag> findById(long id) {
        return vraagRepository.findById(id);
    }

    @Override
    public List<Vraag> findByVraag(String vraag) {
        return vraagRepository.findByVraag(vraag);
    }
}
