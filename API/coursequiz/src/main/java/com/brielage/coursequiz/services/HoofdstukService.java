package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.Hoofdstuk;

import java.util.List;
import java.util.Optional;

public interface HoofdstukService {
    List<Hoofdstuk> findAll();

    Optional<Hoofdstuk> findById(long id);
}
