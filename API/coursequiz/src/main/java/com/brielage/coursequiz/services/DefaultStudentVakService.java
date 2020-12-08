package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.StudentVak;
import com.brielage.coursequiz.repositories.StudentVakRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DefaultStudentVakService
        implements StudentVakService {
    private final StudentVakRepository studentVakRepository;

    DefaultStudentVakService(StudentVakRepository studentVakRepository) {
        this.studentVakRepository = studentVakRepository;
    }

    @Override
    public void create(StudentVak studentVak) {
        studentVakRepository.create(studentVak);
    }

    @Override
    public List<StudentVak> findAll() {
        return studentVakRepository.findAll();
    }

    @Override
    public List<StudentVak> findByStudentId(long studentId) {
        return studentVakRepository.findByStudentId(studentId);
    }

    @Override
    public List<StudentVak> findByVakId(long vakId) {
        return studentVakRepository.findByVakId(vakId);
    }
}
