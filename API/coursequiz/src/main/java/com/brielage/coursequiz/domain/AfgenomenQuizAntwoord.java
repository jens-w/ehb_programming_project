package com.brielage.coursequiz.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@SuppressWarnings("unused")
@Entity
@Table(name = "afgenomenquizantwoorden")
public class AfgenomenQuizAntwoord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private long afgenomenQuizId;
    @NotBlank
    private long antwoordId;
    @NotBlank
    private boolean juistBeantwoord;

    protected AfgenomenQuizAntwoord() {
    }

    public AfgenomenQuizAntwoord(long id,
                                 @NotBlank long afgenomenQuizId,
                                 @NotBlank long antwoordId,
                                 @NotBlank boolean juistBeantwoord) {
        this.id = id;
        this.afgenomenQuizId = afgenomenQuizId;
        this.antwoordId = antwoordId;
        this.juistBeantwoord = juistBeantwoord;
    }
}
