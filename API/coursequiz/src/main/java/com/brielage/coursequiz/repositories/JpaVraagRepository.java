package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.Vraag;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaVraagRepository
        implements VraagRepository {
    private final EntityManager manager;

    JpaVraagRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void create(Vraag vraag) {
        manager.persist(vraag);
    }

    @Override
    public Optional<Vraag> findById(long id) {
        return Optional.ofNullable(manager.find(Vraag.class, id));
    }

    @Override
    public List<Vraag> findAll() {
        return manager.createQuery("select v from Vraag v order by v.id",
                Vraag.class).getResultList();
    }

    @Override
    public List<Vraag> findByVraag(String vraag) {
        return manager.createQuery(
                "select v from Vraag v " +
                        "where v.vraag like :vraaginhoud",
                Vraag.class)
                .setParameter("vraaginhoud", vraag)
                .getResultList();
    }
}
