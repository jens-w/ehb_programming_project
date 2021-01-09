package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void create(User user);

    Optional<User> findById(long id);

    List<User> findAll();

    List<User> findByEmail(String email);

    List<User> findByUserkey(String userkey);

    List<String> findUserkeys();

    List<User> findAllRegularUsers();

    List<User> findAllAdmins();
}
