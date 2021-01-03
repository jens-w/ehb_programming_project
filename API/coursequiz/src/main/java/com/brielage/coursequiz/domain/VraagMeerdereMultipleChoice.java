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
    private int aantalJuisteAntwoordenNodig;
    @NotBlank
    @Column
    private boolean minimumAantalJuisteAntwoordenTonen;

    protected VraagMeerdereMultipleChoice() {
    }

    public VraagMeerdereMultipleChoice(@NotBlank long vraagId,
                                       @NotBlank int aantalJuisteAntwoordenNodig,
                                       @NotBlank boolean minimumAantalJuisteAntwoordenTonen) {
        this.aantalJuisteAntwoordenNodig = aantalJuisteAntwoordenNodig;
        this.minimumAantalJuisteAntwoordenTonen = minimumAantalJuisteAntwoordenTonen;
    }

    public VraagMeerdereMultipleChoice(long id,
                                       @NotBlank String vraag,
                                       @NotBlank int aantalAntwoordenTonen,
                                       @NotBlank boolean juisteAntwoordTonen,
                                       @NotBlank long hoofdstukId,
                                       @NotBlank int aantalJuisteAntwoordenNodig,
                                       @NotBlank boolean minimumAantalJuisteAntwoordenTonen) {
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
}
