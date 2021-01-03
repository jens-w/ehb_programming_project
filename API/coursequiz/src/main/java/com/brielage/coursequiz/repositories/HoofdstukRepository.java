package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.Hoofdstuk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HoofdstukRepository
        extends JpaRepository<Hoofdstuk, Long> {
}
