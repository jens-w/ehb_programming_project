package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.Rol;
import com.brielage.coursequiz.domain.User;
import com.brielage.coursequiz.domain.UserRol;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaUserRepository
        implements UserRepository {
    private final EntityManager manager;
    private final UserRolRepository userRolRepository;

    public JpaUserRepository(EntityManager manager,
                             UserRolRepository userRolRepository) {
        this.manager = manager;
        this.userRolRepository = userRolRepository;
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
        return manager.createQuery("select u from User u where u.email = :email",
                User.class)
                .setParameter("email", email)
                .getResultList();
    }

    @Override
    public List<User> findByUserkey(String userkey) {
        return manager.createQuery("select u from User u where u.userkey = :userkey",
                User.class)
                .setParameter("userkey", userkey)
                .getResultList();
    }

    @Override
    public List<String> findUserkeys() {
        return manager.createQuery("select u.userkey from User u",
                String.class).getResultList();
    }

    @SuppressWarnings({"unchecked", "SqlDialectInspection", "OptionalGetWithoutIsPresent"})
    @Override
    // @Query(nativeQuery = true)
    public List<User> findAllRegularUsers() {
        //TODO optimize this method, below should work somehow but may not be entirely correct
        /*
        return manager.createNativeQuery(
                "select users.* from users " +
                        "inner join rollen " +
                        "on users.id=rollen.userid " +
                        "where rollen.rol = 0",
                User.class)
                .getResultList();
        */
        List<User> users = new ArrayList<>();
        List<UserRol> userrollen = userRolRepository.findAllRol(Rol.USER);
        List<Long> ids = new ArrayList<>();

        for (UserRol userRol : userrollen)
            ids.add(userRol.getUserId());

        for (long id : ids)
            users.add(this.findById(id).get());

        return users;
    }

    @Override
    public List<User> findAllAdmins() {
        //TODO optimize this method to use nativequery or something, see findAllRegularUsers()
        List<User> users = new ArrayList<>();
        List<UserRol> userrollen = userRolRepository.findAllRol(Rol.ADMIN);
        List<Long> ids = new ArrayList<>();

        for (UserRol userRol : userrollen)
            ids.add(userRol.getUserId());

        for (long id : ids)
            users.add(this.findById(id).get());

        return users;
    }
}
