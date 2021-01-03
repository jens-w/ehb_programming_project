package com.brielage.coursequiz.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@SuppressWarnings("unused")
@Entity
@Table(name = "antwoorden")
public class Antwoord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private long vraagId;
    @NotBlank
    private String antwoord;
    @NotBlank
    private boolean isJuist;

    protected Antwoord() {
    }

    public Antwoord(long id,
                    @NotBlank long vraagId,
                    @NotBlank String antwoord,
                    @NotBlank boolean isJuist) {
        this.id = id;
        this.vraagId = vraagId;
        this.antwoord = antwoord;
        this.isJuist = isJuist;
    }
}
