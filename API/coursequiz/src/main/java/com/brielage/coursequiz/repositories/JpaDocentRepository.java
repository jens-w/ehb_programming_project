package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.Docent;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaDocentRepository
        implements DocentRepository {
    private final EntityManager manager;

    public JpaDocentRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void create(Docent docent) {
        manager.persist(docent);
    }

    @SuppressWarnings("SqlDialectInspection")
    @Override
    @Transactional
    public void add(long userid) {
        manager.createNativeQuery(
                "insert into docenten (userid) " +
                        "values (?)")
                .setParameter(1, userid)
                .executeUpdate();
    }

    @Override
    public void remove(Docent docent) {
        manager.remove(docent);
    }

    @Override
    public Optional<Docent> findById(long id) {
        return Optional.ofNullable(manager.find(Docent.class, id));
    }

    @Override
    public List<Docent> findAll() {
        return manager.createQuery("select d from Docent d order by d.id",
                Docent.class)
                .getResultList();
    }
}
