package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.AfgenomenQuiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AfgenomenQuizRepository
        extends JpaRepository<AfgenomenQuiz, Long> {
}
