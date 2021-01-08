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
    private long vakid, hoofdstukid;

    protected Quiz() {
    }

    public Quiz(long id,
                @NotBlank String naam,
                @NotBlank String omschrijving,
                @NotNull boolean isBeschikbaar,
                @NotNull long vakid,
                @NotNull long hoofdstukid) {
        this.id = id;
        this.naam = naam;
        this.omschrijving = omschrijving;
        this.isBeschikbaar = isBeschikbaar;
        this.vakid = vakid;
        this.hoofdstukid = hoofdstukid;
    }

    public Quiz(@NotBlank String naam,
                @NotBlank String omschrijving,
                @NotNull boolean isBeschikbaar,
                @NotNull long vakid,
                @NotNull long hoofdstukid) {
        this.naam = naam;
        this.omschrijving = omschrijving;
        this.isBeschikbaar = isBeschikbaar;
        this.vakid = vakid;
        this.hoofdstukid = hoofdstukid;
    }

    public Quiz(@NotBlank String naam,
                @NotBlank String omschrijving,
                @NotNull long vakid,
                @NotNull long hoofdstukid) {
        this.naam = naam;
        this.omschrijving = omschrijving;
        this.vakid = vakid;
        this.hoofdstukid = hoofdstukid;
        this.isBeschikbaar = false;
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public boolean isBeschikbaar() {
        return isBeschikbaar;
    }

    public long getVakid() {
        return vakid;
    }

    public long getHoofdstukid() {
        return hoofdstukid;
    }
}
