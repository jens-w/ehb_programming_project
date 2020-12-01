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
                               @NotBlank int aantalAntwoordenTonen,
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
}
