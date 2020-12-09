package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaUserRepository
        implements UserRepository {
    private final EntityManager manager;

    public JpaUserRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void create(User user) {
        manager.persist(user);
    }

    @Override
    public Optional<User> findById(long id) {
        return Optional.ofNullable(manager.find(User.class, id));
    }

    @Override
    public List<User> findAll() {
        return manager.createQuery("select u from User u order by u.id",
                User.class).getResultList();
    }

    @Override
    public List<User> findByEmail(String email) {
        return manager.createQuery(
                "select u from User u where u.email = :email",
                User.class)
                .setParameter("email", email)
                .getResultList();
    }
}
