package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.Student;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class JpaStudentRepository
        implements StudentRepository {
    private final EntityManager manager;

    public JpaStudentRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void create(Student student) {
        manager.persist(student);
    }

    @SuppressWarnings("SqlDialectInspection")
    @Override
    @Transactional
    public void add(long userid, long opleidingid) {
        manager.createNativeQuery(
                "insert into studenten (userid, opleidingid) " +
                        "values (?,?)")
                .setParameter(1, userid)
                .setParameter(2, opleidingid)
                .executeUpdate();
    }

    @Override
    public List<Student> findAll() {
        return manager.createQuery("select s from Student s order by s.id",
                Student.class).getResultList();
    }

    @Override
    public List<Student> findByUserId(long userid) {
        return manager.createQuery(
                "select s from Student s " +
                        "where s.id = :userid",
                Student.class)
                .setParameter("userid", userid)
                .getResultList();
    }

    @Override
    public List<Student> findByOpleidingId(long opleidingid) {
        return manager.createQuery("select s from Student s where s.opleidingid = :opleidingid",
                Student.class)
                .setParameter("opleidingid", opleidingid)
                .getResultList();
    }
}
