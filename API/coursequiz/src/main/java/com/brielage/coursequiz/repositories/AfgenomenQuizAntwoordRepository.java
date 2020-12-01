package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.AfgenomenQuizAntwoord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AfgenomenQuizAntwoordRepository
        extends JpaRepository<AfgenomenQuizAntwoord, Long> {
}
