package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.Antwoord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AntwoordRepository
        extends JpaRepository<Antwoord, Long> {
}
