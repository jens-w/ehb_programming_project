package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.DocentVak;

import java.util.List;

public interface DocentVakRepository {
    void create(DocentVak docentVak);

    List<DocentVak> findAll();

    List<DocentVak> findByDocentId(long docentId);

    List<DocentVak> findByVakId(long vakId);
}
