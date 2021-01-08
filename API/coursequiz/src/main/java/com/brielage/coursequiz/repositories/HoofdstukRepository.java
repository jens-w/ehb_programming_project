package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.Hoofdstuk;

import java.util.List;

public interface HoofdstukRepository {
    void create(Hoofdstuk hoofdstuk);

    List<Hoofdstuk> findByVakId(long vakid);
}
