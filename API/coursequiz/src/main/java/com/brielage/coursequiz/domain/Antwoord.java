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
@Table(name = "antwoorden")
public class Antwoord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private long vraagId;
    @NotBlank
    private String antwoord;
    @NotNull
    private boolean isJuist;

    protected Antwoord() {
    }

    public Antwoord(@NotNull long vraagId,
                    @NotBlank String antwoord,
                    @NotNull boolean isJuist) {
        this.vraagId = vraagId;
        this.antwoord = antwoord;
        this.isJuist = isJuist;
    }

    public Antwoord(long id,
                    @NotNull long vraagId,
                    @NotBlank String antwoord,
                    @NotNull boolean isJuist) {
        this.id = id;
        this.vraagId = vraagId;
        this.antwoord = antwoord;
        this.isJuist = isJuist;
    }

    public long getId() {
        return id;
    }

    public long getVraagId() {
        return vraagId;
    }

    public void setVraagId(long vraagId) {
        this.vraagId = vraagId;
    }

    public String getAntwoord() {
        return antwoord;
    }

    public boolean isJuist() {
        return isJuist;
    }

    @Override
    public String toString() {
        return id + " " +
                vraagId + " " +
                antwoord + " " +
                isJuist;
    }
}
