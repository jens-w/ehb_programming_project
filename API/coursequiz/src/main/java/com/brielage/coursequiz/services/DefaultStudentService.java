package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.Student;
import com.brielage.coursequiz.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultStudentService
        implements StudentService {
    private final StudentRepository studentRepository;

    DefaultStudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> findById(long id) {
        return studentRepository.findById(id);
    }
}
