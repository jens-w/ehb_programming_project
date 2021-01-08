package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.Student;

import java.util.List;

public interface StudentService {
    void create(Student student);

    void add(long userid, long opleidingid);

    List<Student> findAll();

    List<Student> findByUserId(long id);

    List<Student> findByOpleidingId(long opleidingid);
}
