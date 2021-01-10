package com.brielage.coursequiz.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @NotNull
    @Column
    private long aantalAntwoordenTonen;
    @NotNull
    @Column
    private boolean juisteAntwoordTonen;
    @NotNull
    @Column
    private long hoofdstukId;

    protected Vraag() {
    }

    public Vraag(long id,
                 @NotBlank String vraag,
                 @NotNull long aantalAntwoordenTonen,
                 @NotNull boolean juisteAntwoordTonen,
                 @NotNull long hoofdstukId) {
        this.id = id;
        this.vraag = vraag;
        this.aantalAntwoordenTonen = aantalAntwoordenTonen;
        this.juisteAntwoordTonen = juisteAntwoordTonen;
        this.hoofdstukId = hoofdstukId;
    }

    public Vraag(@NotBlank String vraag,
                 @NotNull long aantalAntwoordenTonen,
                 @NotNull boolean juisteAntwoordTonen,
                 @NotNull long hoofdstukId) {
        this.vraag = vraag;
        this.aantalAntwoordenTonen = aantalAntwoordenTonen;
        this.juisteAntwoordTonen = juisteAntwoordTonen;
        this.hoofdstukId = hoofdstukId;
    }

    public long getId() {
        return id;
    }

    public String getVraag() {
        return vraag;
    }

    public long getAantalAntwoordenTonen() {
        return aantalAntwoordenTonen;
    }

    public boolean isJuisteAntwoordTonen() {
        return juisteAntwoordTonen;
    }

    public long getHoofdstukId() {
        return hoofdstukId;
    }

    @Override
    public String toString() {
        return "Vraag{" +
                "id=" + id +
                ", vraag='" + vraag + '\'' +
                ", aantalAntwoordenTonen=" + aantalAntwoordenTonen +
                ", juisteAntwoordTonen=" + juisteAntwoordTonen +
                ", hoofdstukId=" + hoofdstukId +
                '}';
    }
}
