package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.StudentVak;

import java.util.List;
import java.util.Optional;

public interface StudentVakService {
    List<StudentVak> findAll();

    Optional<StudentVak> findById(long id);
}
