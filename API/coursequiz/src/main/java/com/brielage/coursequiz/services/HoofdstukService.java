package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.Hoofdstuk;

import java.util.List;
import java.util.Optional;

public interface HoofdstukService {
    void create(Hoofdstuk hoofdstuk);

    Optional<Hoofdstuk> findById(long id);

    List<Hoofdstuk> findByVakId(long vakid);
}
