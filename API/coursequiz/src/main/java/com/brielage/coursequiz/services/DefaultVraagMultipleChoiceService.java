package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.VraagMultipleChoice;
import com.brielage.coursequiz.repositories.VraagMultipleChoiceRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultVraagMultipleChoiceService
        implements VraagMultipleChoiceService {
    private final VraagMultipleChoiceRepository vraagMultipleChoiceRepository;

    DefaultVraagMultipleChoiceService(VraagMultipleChoiceRepository vraagMultipleChoiceRepository) {
        this.vraagMultipleChoiceRepository = vraagMultipleChoiceRepository;
    }

    @Override
    public List<VraagMultipleChoice> findAll() {
        return vraagMultipleChoiceRepository.findAll();
    }

    @Override
    public Optional<VraagMultipleChoice> findById(long id) {
        return vraagMultipleChoiceRepository.findById(id);
    }
}
