package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.User;
import com.brielage.coursequiz.repositories.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultUserService
        implements UserService {
    private final UserRepository userRepository;

    DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public void create(User u) {
        userRepository.create(u);
    }

    @Override
    public List<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findByUserkey(String userkey) {
        return userRepository.findByUserkey(userkey);
    }

    @Override
    public List<String> findUserkeys() {
        return userRepository.findUserkeys();
    }

    @Override
    public List<User> findAllRegularUsers() {
        return userRepository.findAllRegularUsers();
    }

    @Override
    public List<User> findAllAdmins() {
        return userRepository.findAllAdmins();
    }
}
