package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.Vraag;

import java.util.List;
import java.util.Optional;

public interface VraagService {
    List<Vraag> findAll();

    Optional<Vraag> findById(long id);
}
