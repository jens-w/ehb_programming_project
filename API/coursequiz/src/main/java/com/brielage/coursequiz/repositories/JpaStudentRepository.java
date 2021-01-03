package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.Student;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaStudentRepository
        implements StudentRepository {
    private final EntityManager manager;

    public JpaStudentRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public List<Student> findAll() {
        return manager.createQuery("select s from Student s order by s.id",
                Student.class).getResultList();
    }

    @Override
    public Optional<Student> findById(long id) {
        return Optional.ofNullable(manager.find(Student.class, id));
    }

    @Override
    public List<Student> findByOpleidingId(long opleidingid) {
        return manager.createQuery("select s from Student s where s.opleidingid = :opleidingid",
                Student.class)
                .setParameter("opleidingid", opleidingid)
                .getResultList();
    }
}
