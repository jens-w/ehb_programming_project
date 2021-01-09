package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.Student;
import com.brielage.coursequiz.repositories.StudentRepository;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DefaultStudentService
        implements StudentService {
    private final StudentRepository studentRepository;

    DefaultStudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void create(Student student) {
        studentRepository.create(student);
    }

    @Override
    public void add(long userid, long opleidingid) {
        studentRepository.add(userid, opleidingid);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> findByUserId(long id) {
        return studentRepository.findByUserId(id);
    }

    @Override
    public List<Student> findByOpleidingId(long opleidingid) {
        return studentRepository.findByOpleidingId(opleidingid);
    }
}
