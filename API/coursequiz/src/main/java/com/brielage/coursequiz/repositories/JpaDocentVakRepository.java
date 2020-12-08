package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.DocentVak;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class JpaDocentVakRepository
        implements DocentVakRepository {
    private final EntityManager manager;

    public JpaDocentVakRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void create(DocentVak docentVak) {
        manager.persist(docentVak);
    }

    @Override
    public List<DocentVak> findAll() {
        return manager.createQuery("select dv from DocentVak dv order by dv.userid",
                DocentVak.class).getResultList();
    }

    @Override
    public List<DocentVak> findByDocentId(long docentId) {
        return manager.createQuery(
                "select dv from DocentVak dv where dv.userid = :docentId order by dv.vakid",
                DocentVak.class)
                .setParameter("docentId", docentId)
                .getResultList();
    }

    @Override
    public List<DocentVak> findByVakId(long vakId) {
        return manager.createQuery(
                "select dv from DocentVak dv where dv.vakid = :vakId order by dv.userid",
                DocentVak.class).getResultList();
    }
}
