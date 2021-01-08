package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.Hoofdstuk;

import java.util.List;

public interface HoofdstukService {
    void create(Hoofdstuk hoofdstuk);

    List<Hoofdstuk> findByVakId(long vakid);
}
