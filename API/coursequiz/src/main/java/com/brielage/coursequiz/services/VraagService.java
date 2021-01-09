package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.Vraag;

import java.util.List;
import java.util.Optional;

public interface VraagService {
    void create(Vraag vraag);

    Optional<Vraag> findById(long id);

    List<Vraag> findAll();

    List<Vraag> findByVraag(String vraag);
}
