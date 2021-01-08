package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.Docent;

import java.util.List;
import java.util.Optional;

public interface DocentService {
    void create(Docent docent);

    void add(long userid);

    void remove(Docent docent);

    Optional<Docent> findById(long id);

    List<Docent> findAll();
}
