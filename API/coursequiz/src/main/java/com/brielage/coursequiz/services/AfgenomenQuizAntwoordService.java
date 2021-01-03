package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.AfgenomenQuizAntwoord;

import java.util.List;
import java.util.Optional;

public interface AfgenomenQuizAntwoordService {
    List<AfgenomenQuizAntwoord> findAll();

    Optional<AfgenomenQuizAntwoord> findById(long id);
}
