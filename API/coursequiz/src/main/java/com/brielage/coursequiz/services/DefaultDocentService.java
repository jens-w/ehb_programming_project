package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.Docent;
import com.brielage.coursequiz.repositories.DocentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultDocentService
        implements DocentService {
    private final DocentRepository docentRepository;

    DefaultDocentService(DocentRepository docentRepository) {
        this.docentRepository = docentRepository;
    }

    @Override
    public List<Docent> findAll() {
        return docentRepository.findAll();
    }

    @Override
    public Optional<Docent> findById(long id) {
        return docentRepository.findById(id);
    }
}
