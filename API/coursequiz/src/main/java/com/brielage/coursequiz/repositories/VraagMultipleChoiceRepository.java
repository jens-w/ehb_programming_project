package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.VraagMultipleChoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VraagMultipleChoiceRepository
        extends JpaRepository<VraagMultipleChoice, Long> {
}
