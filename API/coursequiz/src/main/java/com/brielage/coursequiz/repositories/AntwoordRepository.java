package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.Antwoord;

import java.util.List;
import java.util.Optional;

public interface AntwoordRepository {
    void create(Antwoord antwoord);

    Optional<Antwoord> findById(long id);

    List<Antwoord> findAll();

    List<Antwoord> findByVraagId(long vraagid);
}
