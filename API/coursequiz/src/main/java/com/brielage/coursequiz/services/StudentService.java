package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> findAll();

    Optional<Student> findById(long id);
}
