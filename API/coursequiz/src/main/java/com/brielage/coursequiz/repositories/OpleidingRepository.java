package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.Opleiding;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpleidingRepository
        extends JpaRepository<Opleiding, Long> {
}
