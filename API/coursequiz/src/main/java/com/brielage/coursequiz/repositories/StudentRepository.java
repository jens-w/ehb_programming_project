package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.Student;

import java.util.List;

public interface StudentRepository {
    void create(Student student);

    void add(long userid, long opleidingid);

    List<Student> findAll();

    List<Student> findByUserId(long id);

    List<Student> findByOpleidingId(long opleidingid);
}
