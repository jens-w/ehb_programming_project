package com.brielage.coursequiz.repositories;

import com.brielage.coursequiz.domain.Opleiding;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OpleidingRepository {
    void create(Opleiding opleiding);

    Optional<Opleiding> findById(long id);

    List<Opleiding> findAll();
}
