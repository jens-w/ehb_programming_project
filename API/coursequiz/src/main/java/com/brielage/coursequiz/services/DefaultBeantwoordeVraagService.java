package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.BeantwoordeVraag;
import com.brielage.coursequiz.repositories.BeantwoordeVraagRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultBeantwoordeVraagService
        implements BeantwoordeVraagService {
    private final BeantwoordeVraagRepository beantwoordeVraagRepository;

    DefaultBeantwoordeVraagService(BeantwoordeVraagRepository beantwoordeVraagRepository) {
        this.beantwoordeVraagRepository = beantwoordeVraagRepository;
    }

    @Override
    public List<BeantwoordeVraag> findAll() {
        return beantwoordeVraagRepository.findAll();
    }

    @Override
    public Optional<BeantwoordeVraag> findById(long id) {
        return beantwoordeVraagRepository.findById(id);
    }
}
