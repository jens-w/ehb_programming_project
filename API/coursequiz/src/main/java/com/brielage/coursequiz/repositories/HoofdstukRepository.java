package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.Hoofdstuk;

import java.util.List;
import java.util.Optional;

public interface HoofdstukRepository {
    void create(Hoofdstuk hoofdstuk);

    Optional<Hoofdstuk> findById(long id);

    List<Hoofdstuk> findByVakId(long vakid);
}
