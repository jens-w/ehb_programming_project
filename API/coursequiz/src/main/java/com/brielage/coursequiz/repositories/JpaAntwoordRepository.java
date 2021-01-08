package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.Antwoord;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaAntwoordRepository
        implements AntwoordRepository {
    private final EntityManager manager;

    public JpaAntwoordRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void create(Antwoord antwoord) {
        manager.persist(antwoord);
    }

    @Override
    public Optional<Antwoord> findById(long id) {
        return Optional.ofNullable(manager.find(Antwoord.class, id));
    }

    @Override
    public List<Antwoord> findAll() {
        return manager.createQuery("select a from Antwoord a order by a.id",
                Antwoord.class).getResultList();
    }

    @Override
    public List<Antwoord> findByVraagId(long vraagid) {
        return manager.createQuery(
                "select a from Antwoord a " +
                        "where a.vraagId = :vraagid",
                Antwoord.class)
                .setParameter("vraagid", vraagid)
                .getResultList();
    }
}
