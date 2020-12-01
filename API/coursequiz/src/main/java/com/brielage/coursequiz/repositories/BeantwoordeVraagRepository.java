package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.BeantwoordeVraag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeantwoordeVraagRepository
        extends JpaRepository<BeantwoordeVraag, Long> {
}
