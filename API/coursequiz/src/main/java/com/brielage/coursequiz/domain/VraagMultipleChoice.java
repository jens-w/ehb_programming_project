package com.brielage.coursequiz.domain;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@SuppressWarnings("unused")
@Entity
@Table(name = "vragensimpelemultiplechoice")
@PrimaryKeyJoinColumn(name = "vraagid")
public class VraagMultipleChoice
        extends Vraag {
    protected VraagMultipleChoice() {
    }

    public VraagMultipleChoice(long id,
                               @NotBlank String vraag,
                               @NotBlank long aantalAntwoordenTonen,
                               @NotBlank boolean juisteAntwoordTonen,
                               @NotBlank long hoofdstukId) {
        super(
                id,
                vraag,
                aantalAntwoordenTonen,
                juisteAntwoordTonen,
                hoofdstukId
        );
    }

    public VraagMultipleChoice(@NotBlank String vraag,
                               @NotBlank long aantalAntwoordenTonen,
                               @NotBlank boolean juisteAntwoordTonen,
                               @NotBlank long hoofdstukId) {
        super(
                vraag,
                aantalAntwoordenTonen,
                juisteAntwoordTonen,
                hoofdstukId
        );
    }
}
