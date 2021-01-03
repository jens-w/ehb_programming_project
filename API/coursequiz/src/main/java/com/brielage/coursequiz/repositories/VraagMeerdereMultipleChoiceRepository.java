package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.VraagMeerdereMultipleChoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VraagMeerdereMultipleChoiceRepository
        extends JpaRepository<VraagMeerdereMultipleChoice, Long> {
}
