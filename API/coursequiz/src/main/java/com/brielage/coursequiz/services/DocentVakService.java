package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.DocentVak;

import java.util.List;

public interface DocentVakService {
    void create(DocentVak docentVak);

    List<DocentVak> findAll();

    List<DocentVak> findByDocentId(long docentId);

    List<DocentVak> findByVakId(long vakId);

    List<DocentVak> findByDocentIdAndVakId(long docentId, long vakId);
}
