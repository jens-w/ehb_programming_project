package com.brielage.coursequiz.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@SuppressWarnings("unused")
@Entity
@Table(name = "quizzen")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String naam;
    @NotBlank
    private String omschrijving;
    @NotBlank
    private boolean isBeschikbaar;
    @NotBlank
    private long vakId;

    protected Quiz() {
    }

    public Quiz(long id,
                @NotBlank String naam,
                @NotBlank String omschrijving,
                @NotBlank boolean isBeschikbaar,
                @NotBlank long vakId) {
        this.id = id;
        this.naam = naam;
        this.omschrijving = omschrijving;
        this.isBeschikbaar = isBeschikbaar;
        this.vakId = vakId;
    }
}
