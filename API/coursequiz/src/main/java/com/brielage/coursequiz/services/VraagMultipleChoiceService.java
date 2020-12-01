package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.VraagMultipleChoice;

import java.util.List;
import java.util.Optional;

public interface VraagMultipleChoiceService {
    List<VraagMultipleChoice> findAll();

    Optional<VraagMultipleChoice> findById(long id);
}
