package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    List<Student> findAll();

    Optional<Student> findById(long id);

    List<Student> findByOpleidingId(long opleidingid);
}
