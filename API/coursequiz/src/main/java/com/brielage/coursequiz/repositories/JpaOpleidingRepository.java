package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.Opleiding;
import com.brielage.coursequiz.services.StudentService;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaOpleidingRepository
        implements OpleidingRepository {
    private final EntityManager manager;
    private final StudentService studentService;

    public JpaOpleidingRepository(EntityManager manager,
                                  StudentService studentService) {
        this.manager = manager;
        this.studentService = studentService;
    }

    @Override
    public void create(Opleiding opleiding) {
        manager.persist(opleiding);
    }

    @Override
    public Optional<Opleiding> findById(long id) {
        return Optional.ofNullable(manager.find(Opleiding.class, id));
    }

    @Override
    public List<Opleiding> findAll() {
        return manager.createQuery("select o from Opleiding o order by o.id",
                Opleiding.class).getResultList();
    }

    @Override
    public List<Opleiding> findByNaam(String naam) {
        return manager.createQuery("select o from Opleiding o " +
                        "where o.naam = :naam order by o.id",
                Opleiding.class)
                .setParameter("naam", naam)
                .getResultList();
    }
}
