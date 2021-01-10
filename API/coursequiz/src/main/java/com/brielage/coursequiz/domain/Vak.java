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
@Table(name = "vakken")
public class Vak {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String naam;
    @NotNull
    private int jaar;
    @NotNull
    private long opleidingid;

    protected Vak() {
    }

    public Vak(long id,
               @NotBlank String naam,
               @NotNull int jaar,
               @NotNull long opleidingId) {
        this.id = id;
        this.naam = naam;
        this.jaar = jaar;
        this.opleidingid = opleidingId;
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getJaar() {
        return jaar;
    }

    public void setJaar(int jaar) {
        this.jaar = jaar;
    }

    public long getOpleidingid() {
        return opleidingid;
    }

    public void setOpleidingid(long opleidingId) {
        this.opleidingid = opleidingId;
    }
}
