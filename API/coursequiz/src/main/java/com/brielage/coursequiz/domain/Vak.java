package com.brielage.coursequiz.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@SuppressWarnings("unused")
@Entity
@Table(name = "vakken")
public class Vak {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String naam;
    @NotBlank
    private int jaar;
    @NotBlank
    private long opleidingid;

    protected Vak() {
    }

    public Vak(long id,
               @NotBlank String naam,
               @NotBlank int jaar,
               @NotBlank long opleidingId) {
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

    public long getOpledingId() {
        return opleidingid;
    }

    public void setOpledingId(long opleidingId) {
        this.opleidingid = opleidingId;
    }
}
