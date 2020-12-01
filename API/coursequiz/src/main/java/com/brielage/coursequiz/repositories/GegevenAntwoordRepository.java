package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.GegevenAntwoord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GegevenAntwoordRepository
        extends JpaRepository<GegevenAntwoord, Long> {
}
