package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    Optional<User> findById(long id);

    void create(User u);

    List<User> findByEmail(String email);

    List<User> findByUserkey(String userkey);
}
