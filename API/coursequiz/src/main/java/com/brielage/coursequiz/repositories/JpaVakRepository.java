package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.Vak;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaVakRepository
        implements VakRepository {
    private final EntityManager manager;

    public JpaVakRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void create(Vak vak) {
        manager.persist(vak);
    }

    @Override
    public Optional<Vak> findById(long id) {
        return Optional.ofNullable(manager.find(Vak.class, id));
    }

    @Override
    public List<Vak> findAll() {
        return manager.createQuery("select v from Vak v order by v.id",
                Vak.class).getResultList();
    }
}
