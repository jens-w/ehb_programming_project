package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.StudentVak;
import com.brielage.coursequiz.repositories.StudentVakRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultStudentVakService
        implements StudentVakService {
    private final StudentVakRepository studentVakRepository;

    DefaultStudentVakService(StudentVakRepository studentVakRepository) {
        this.studentVakRepository = studentVakRepository;
    }

    @Override
    public List<StudentVak> findAll() {
        return studentVakRepository.findAll();
    }

    @Override
    public Optional<StudentVak> findById(long id) {
        return studentVakRepository.findById(id);
    }
}
