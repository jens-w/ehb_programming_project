package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.Rol;
import com.brielage.coursequiz.domain.UserRol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaUserRolRepository
        implements UserRolRepository {
    private final EntityManager manager;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public JpaUserRolRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void create(UserRol userRol) {
        manager.persist(userRol);
    }

    @Override
    public Optional<UserRol> findByUserId(long userId) {
        return Optional.ofNullable(manager.find(UserRol.class, userId));
    }

    @Override
    public List<UserRol> findAll() {
        return manager.createQuery("select ur from UserRol ur order by ur.userId",
                UserRol.class).getResultList();
    }

    public List<UserRol> findAllRol(Rol rol) {
        return manager.createQuery(
                "select ur from UserRol ur where ur.rol = :rol order by ur.userId",
                UserRol.class)
                .setParameter("rol", rol)
                .getResultList();
    }
}
