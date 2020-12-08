package com.brielage.coursequiz.services;

import com.brielage.coursequiz.domain.Opleiding;

import java.util.List;
import java.util.Optional;

public interface OpleidingService {
    List<Opleiding> findAll();

    Optional<Opleiding> findById(long id);

    void create(Opleiding opleiding);
}
