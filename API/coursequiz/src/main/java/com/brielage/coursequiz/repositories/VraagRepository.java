package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.Vraag;

import java.util.List;
import java.util.Optional;

public interface VraagRepository {
    void create(Vraag vraag);

    Optional<Vraag> findById(long id);

    List<Vraag> findAll();

    List<Vraag> findByVraag(String vraag);
}
