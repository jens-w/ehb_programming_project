package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.Rol;
import com.brielage.coursequiz.domain.UserRol;
import com.brielage.coursequiz.repositories.UserRolRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultUserRolService
        implements UserRolService {
    private final UserRolRepository userRolRepository;

    DefaultUserRolService(UserRolRepository userRolRepository) {
        this.userRolRepository = userRolRepository;
    }

    @Override
    public void create(UserRol userRol) {
        userRolRepository.create(userRol);
    }

    @Override
    public Optional<UserRol> findByUserId(long userId) {
        return userRolRepository.findByUserId(userId);
    }

    @Override
    public List<UserRol> findAll() {
        return userRolRepository.findAll();
    }

    @Override
    public List<UserRol> findAllRol(Rol rol) {
        return userRolRepository.findAllRol(rol);
    }
}
