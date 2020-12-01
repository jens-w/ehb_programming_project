package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.VraagMeerdereMultipleChoice;

import java.util.List;
import java.util.Optional;

public interface VraagMeerdereMultipleChoiceService {
    List<VraagMeerdereMultipleChoice> findAll();

    Optional<VraagMeerdereMultipleChoice> findById(long id);
}
