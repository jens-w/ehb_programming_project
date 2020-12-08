package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.Vak;

import java.util.List;
import java.util.Optional;

public interface VakRepository {
    void create(Vak vak);

    Optional<Vak> findById(long id);

    List<Vak> findAll();
}
