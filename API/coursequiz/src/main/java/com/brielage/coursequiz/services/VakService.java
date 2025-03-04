package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.Vak;

import java.util.List;
import java.util.Optional;

public interface VakService {
    void create(Vak vak);

    Optional<Vak> findById(long id);

    List<Vak> findAll();
}
