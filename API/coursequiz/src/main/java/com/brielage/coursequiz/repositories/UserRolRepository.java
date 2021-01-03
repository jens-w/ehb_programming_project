package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.Rol;
import com.brielage.coursequiz.domain.UserRol;

import java.util.List;
import java.util.Optional;

public interface UserRolRepository {
    void create(UserRol userRol);

    Optional<UserRol> findByUserId(long userId);

    List<UserRol> findAll();

    List<UserRol> findAllRol(Rol rol);
}
