package com.brielage.coursequiz.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@SuppressWarnings("unused")
@Entity
@Table(name = "gegevenAntwoorden")
public class GegevenAntwoord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private long antwoordId;
    @NotBlank
    private int aantalJuistGekozen;
    @NotBlank
    private int aantalFoutGekozen;
    @NotBlank
    private LocalDateTime jaarMaand;

    protected GegevenAntwoord() {
    }

    public GegevenAntwoord(long id,
                           @NotBlank long antwoordId,
                           @NotBlank int aantalJuistGekozen,
                           @NotBlank int aantalFoutGekozen,
                           @NotBlank LocalDateTime jaarMaand) {
        this.id = id;
        this.antwoordId = antwoordId;
        this.aantalJuistGekozen = aantalJuistGekozen;
        this.aantalFoutGekozen = aantalFoutGekozen;
        this.jaarMaand = jaarMaand;
    }
}
