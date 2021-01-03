package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.Docent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocentRepository
extends JpaRepository<Docent, Long> {
}
