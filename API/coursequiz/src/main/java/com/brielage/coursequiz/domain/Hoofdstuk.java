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
@Table(name = "hoofdstukken")
public class Hoofdstuk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String titel;
    @NotNull
    private float nummer;
    @NotBlank
    private long vakId;

    protected Hoofdstuk() {
    }

    public Hoofdstuk(long id,
                     @NotBlank String titel,
                     @NotNull float nummer,
                     @NotBlank long vakId) {
        this.id = id;
        this.titel = titel;
        this.nummer = nummer;
        this.vakId = vakId;
    }
}
