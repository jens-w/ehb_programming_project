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
    private long opledingId;

    protected Vak() {
    }

    public Vak(long id,
               @NotBlank String naam,
               @NotBlank int jaar,
               @NotBlank long opledingId) {
        this.id = id;
        this.naam = naam;
        this.jaar = jaar;
        this.opledingId = opledingId;
    }
}
