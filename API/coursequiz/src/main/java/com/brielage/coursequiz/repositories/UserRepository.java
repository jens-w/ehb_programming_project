package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository
        extends JpaRepository<User, Long> {
}
