package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.DocentVak;
import com.brielage.coursequiz.repositories.DocentVakRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DefaultDocentVakService
        implements DocentVakService {
    private final DocentVakRepository docentVakRepository;

    DefaultDocentVakService(DocentVakRepository docentVakRepository) {
        this.docentVakRepository = docentVakRepository;
    }

    @Override
    public void create(DocentVak docentVak) {
        docentVakRepository.create(docentVak);
    }

    @Override
    public List<DocentVak> findAll() {
        return docentVakRepository.findAll();
    }

    @Override
    public List<DocentVak> findByDocentId(long docentId) {
        return docentVakRepository.findByDocentId(docentId);
    }

    @Override
    public List<DocentVak> findByVakId(long vakId) {
        return docentVakRepository.findByVakId(vakId);
    }
}
