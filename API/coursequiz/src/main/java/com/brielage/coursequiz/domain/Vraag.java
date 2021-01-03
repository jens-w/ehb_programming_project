package com.brielage.coursequiz.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@SuppressWarnings("unused")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "vragen")
public class Vraag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;
    @NotBlank
    @Column
    private String vraag;
    @NotBlank
    @Column
    private int aantalAntwoordenTonen;
    @NotBlank
    @Column
    private boolean juisteAntwoordTonen;
    @NotBlank
    @Column
    private long hoofdstukId;

    protected Vraag() {
    }

    public Vraag(long id,
                 @NotBlank String vraag,
                 @NotBlank int aantalAntwoordenTonen,
                 @NotBlank boolean juisteAntwoordTonen,
                 @NotBlank long hoofdstukId) {
        this.id = id;
        this.vraag = vraag;
        this.aantalAntwoordenTonen = aantalAntwoordenTonen;
        this.juisteAntwoordTonen = juisteAntwoordTonen;
        this.hoofdstukId = hoofdstukId;
    }
}
