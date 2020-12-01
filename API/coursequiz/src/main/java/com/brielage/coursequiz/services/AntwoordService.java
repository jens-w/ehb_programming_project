package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.Antwoord;

import java.util.List;
import java.util.Optional;

public interface AntwoordService {
    List<Antwoord> findAll();

    Optional<Antwoord> findById(long id);
}
