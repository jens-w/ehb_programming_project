package com.brielage.coursequiz.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@SuppressWarnings("unused")
@Entity
@Table(name = "quizzen")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String naam, omschrijving;
    @NotNull
    private boolean isBeschikbaar;
    @NotNull
    private long vakId, hoofdstukId;

    protected Quiz() {
    }

    public Quiz(long id,
                @NotBlank String naam,
                @NotBlank String omschrijving,
                @NotNull boolean isBeschikbaar,
                @NotNull long vakId,
                @NotNull long hoofdstukId) {
        this.id = id;
        this.naam = naam;
        this.omschrijving = omschrijving;
        this.isBeschikbaar = isBeschikbaar;
        this.vakId = vakId;
        this.hoofdstukId = hoofdstukId;
    }

    public Quiz(@NotBlank String naam,
                @NotBlank String omschrijving,
                @NotNull boolean isBeschikbaar,
                @NotNull long vakId,
                @NotNull long hoofdstukId) {
        this.naam = naam;
        this.omschrijving = omschrijving;
        this.isBeschikbaar = isBeschikbaar;
        this.vakId = vakId;
        this.hoofdstukId = hoofdstukId;
    }

    public Quiz(@NotBlank String naam,
                @NotBlank String omschrijving,
                @NotNull long vakId,
                @NotNull long hoofdstukId) {
        this.naam = naam;
        this.omschrijving = omschrijving;
        this.vakId = vakId;
        this.hoofdstukId = hoofdstukId;
        this.isBeschikbaar = false;
    }
}
