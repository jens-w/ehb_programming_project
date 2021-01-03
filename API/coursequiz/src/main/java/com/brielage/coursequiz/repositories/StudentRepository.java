package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository
        extends JpaRepository<Student, Long> {
}
