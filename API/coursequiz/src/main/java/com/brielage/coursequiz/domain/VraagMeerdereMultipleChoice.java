package com.brielage.coursequiz.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@SuppressWarnings("unused")
@Entity
@Table(name = "vragenmeerderemultiplechoice")
@PrimaryKeyJoinColumn(name = "vraagid")
public class VraagMeerdereMultipleChoice
        extends Vraag {
    @NotBlank
    @Column
    private long aantalJuisteAntwoordenNodig;
    @NotBlank
    @Column
    private long minimumAantalJuisteAntwoordenTonen;

    protected VraagMeerdereMultipleChoice() {
    }

    public VraagMeerdereMultipleChoice(@NotBlank long vraagId,
                                       @NotBlank long aantalJuisteAntwoordenNodig,
                                       @NotBlank long minimumAantalJuisteAntwoordenTonen) {
        this.aantalJuisteAntwoordenNodig = aantalJuisteAntwoordenNodig;
        this.minimumAantalJuisteAntwoordenTonen = minimumAantalJuisteAntwoordenTonen;
    }

    public VraagMeerdereMultipleChoice(long id,
                                       @NotBlank String vraag,
                                       @NotBlank long aantalAntwoordenTonen,
                                       @NotBlank boolean juisteAntwoordTonen,
                                       @NotBlank long hoofdstukId,
                                       @NotBlank long aantalJuisteAntwoordenNodig,
                                       @NotBlank long minimumAantalJuisteAntwoordenTonen) {
        super(
                id,
                vraag,
                aantalAntwoordenTonen,
                juisteAntwoordTonen,
                hoofdstukId
        );
        this.aantalJuisteAntwoordenNodig = aantalJuisteAntwoordenNodig;
        this.minimumAantalJuisteAntwoordenTonen = minimumAantalJuisteAntwoordenTonen;
    }

    public VraagMeerdereMultipleChoice(@NotBlank String vraag,
                                       @NotBlank long aantalAntwoordenTonen,
                                       @NotBlank boolean juisteAntwoordTonen,
                                       @NotBlank long hoofdstukId,
                                       @NotBlank long aantalJuisteAntwoordenNodig,
                                       @NotBlank long minimumAantalJuisteAntwoordenTonen) {
        super(
                vraag,
                aantalAntwoordenTonen,
                juisteAntwoordTonen,
                hoofdstukId
        );
        this.aantalJuisteAntwoordenNodig = aantalJuisteAntwoordenNodig;
        this.minimumAantalJuisteAntwoordenTonen = minimumAantalJuisteAntwoordenTonen;
    }

    public long getAantalJuisteAntwoordenNodig() {
        return aantalJuisteAntwoordenNodig;
    }

    public void setAantalJuisteAntwoordenNodig(long aantalJuisteAntwoordenNodig) {
        this.aantalJuisteAntwoordenNodig = aantalJuisteAntwoordenNodig;
    }

    public long getMinimumAantalJuisteAntwoordenTonen() {
        return minimumAantalJuisteAntwoordenTonen;
    }

    public void setMinimumAantalJuisteAntwoordenTonen(long minimumAantalJuisteAntwoordenTonen) {
        this.minimumAantalJuisteAntwoordenTonen = minimumAantalJuisteAntwoordenTonen;
    }
}
