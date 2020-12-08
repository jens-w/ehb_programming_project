package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.StudentVak;

import java.util.List;

public interface StudentVakRepository {
    void create(StudentVak studentVak);

    List<StudentVak> findAll();

    List<StudentVak> findByStudentId(long studentId);

    List<StudentVak> findByVakId(long vakId);
}
