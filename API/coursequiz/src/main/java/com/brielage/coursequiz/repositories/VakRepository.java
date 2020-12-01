package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.Vak;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VakRepository
        extends JpaRepository<Vak, Long> {
}
