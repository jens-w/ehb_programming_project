package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.Hoofdstuk;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaHoofdstukRepository
        implements HoofdstukRepository {
    private final EntityManager manager;

    public JpaHoofdstukRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void create(Hoofdstuk hoofdstuk) {
        manager.persist(hoofdstuk);
    }

    @Override
    public Optional<Hoofdstuk> findById(long id) {
        return Optional.ofNullable(manager.find(Hoofdstuk.class, id));
    }

    @Override
    public List<Hoofdstuk> findByVakId(long vakid) {
        return manager.createQuery(
                "select h from Hoofdstuk h " +
                        "where h.vakId = :vakid " +
                        "order by h.id",
                Hoofdstuk.class)
                .setParameter("vakid", vakid)
                .getResultList();
    }
}
