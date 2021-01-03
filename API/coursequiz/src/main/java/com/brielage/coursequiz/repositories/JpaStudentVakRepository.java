package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.StudentVak;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class JpaStudentVakRepository
        implements StudentVakRepository {
    private final EntityManager manager;

    public JpaStudentVakRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void create(StudentVak studentVak) {
        manager.persist(studentVak);
    }

    @Override
    public List<StudentVak> findAll() {
        return manager.createQuery("select sv from StudentVak sv order by sv.userid",
                StudentVak.class).getResultList();
    }

    @Override
    public List<StudentVak> findByStudentId(long studentId) {
        return manager.createQuery(
                "select sv from StudentVak sv where sv.userid = :studentId order by sv.vakid",
                StudentVak.class)
                .setParameter("studentId", studentId)
                .getResultList();
    }

    @Override
    public List<StudentVak> findByVakId(long vakId) {
        return manager.createQuery(
                "select sv from StudentVak sv where sv.vakid = :vakId order by sv.userid",
                StudentVak.class).getResultList();
    }
}
