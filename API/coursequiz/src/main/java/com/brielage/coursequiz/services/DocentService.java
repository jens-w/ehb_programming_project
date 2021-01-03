package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.Docent;

import java.util.List;
import java.util.Optional;

public interface DocentService {
    List<Docent> findAll();

    Optional<Docent> findById(long id);
}
