package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.GegevenAntwoord;

import java.util.List;
import java.util.Optional;

public interface GegevenAntwoordService {
    List<GegevenAntwoord> findAll();

    Optional<GegevenAntwoord> findById(long id);
}
