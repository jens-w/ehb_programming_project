package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.BeantwoordeVraag;

import java.util.List;
import java.util.Optional;

public interface BeantwoordeVraagService {
    List<BeantwoordeVraag> findAll();

    Optional<BeantwoordeVraag> findById(long id);
}
