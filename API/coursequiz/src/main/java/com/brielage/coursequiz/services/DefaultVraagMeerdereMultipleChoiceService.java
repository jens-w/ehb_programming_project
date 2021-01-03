package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.VraagMeerdereMultipleChoice;
import com.brielage.coursequiz.repositories.VraagMeerdereMultipleChoiceRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultVraagMeerdereMultipleChoiceService
        implements VraagMeerdereMultipleChoiceService {
    private final VraagMeerdereMultipleChoiceRepository vraagMeerdereMultipleChoiceRepository;

    DefaultVraagMeerdereMultipleChoiceService(VraagMeerdereMultipleChoiceRepository vraagMeerdereMultipleChoiceRepository) {
        this.vraagMeerdereMultipleChoiceRepository = vraagMeerdereMultipleChoiceRepository;
    }

    @Override
    public List<VraagMeerdereMultipleChoice> findAll() {
        return vraagMeerdereMultipleChoiceRepository.findAll();
    }

    @Override
    public Optional<VraagMeerdereMultipleChoice> findById(long id) {
        return vraagMeerdereMultipleChoiceRepository.findById(id);
    }
}
