package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.Hoofdstuk;
import com.brielage.coursequiz.repositories.HoofdstukRepository;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DefaultHoofdstukService
        implements HoofdstukService {
    private final HoofdstukRepository hoofdstukRepository;

    DefaultHoofdstukService(HoofdstukRepository hoofdstukRepository) {
        this.hoofdstukRepository = hoofdstukRepository;
    }

    @Override
    public void create(Hoofdstuk hoofdstuk) {
        hoofdstukRepository.create(hoofdstuk);
    }

    @Override
    public List<Hoofdstuk> findByVakId(long vakid) {
        return hoofdstukRepository.findByVakId(vakid);
    }
}
