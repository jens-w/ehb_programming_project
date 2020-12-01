package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.StudentVak;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentVakRepository
        extends JpaRepository<StudentVak, Long> {
}
