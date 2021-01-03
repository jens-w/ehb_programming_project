package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.Vraag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VraagRepository
        extends JpaRepository<Vraag, Long> {
}
