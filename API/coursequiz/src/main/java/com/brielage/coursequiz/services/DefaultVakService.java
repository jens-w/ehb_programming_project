package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.Vak;
import com.brielage.coursequiz.repositories.VakRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultVakService
        implements VakService {
    private final VakRepository vakRepository;

    DefaultVakService(VakRepository vakRepository) {
        this.vakRepository = vakRepository;
    }

    @Override
    public List<Vak> findAll() {
        return vakRepository.findAll();
    }

    @Override
    public Optional<Vak> findById(long id) {
        return vakRepository.findById(id);
    }
}
