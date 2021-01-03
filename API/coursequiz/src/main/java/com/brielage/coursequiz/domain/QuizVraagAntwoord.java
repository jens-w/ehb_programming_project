package com.brielage.coursequiz.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@SuppressWarnings("unused")
@Entity
@Table(name = "quizvraagantwoorden")
public class QuizVraagAntwoord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private long quizVraagId;
    @NotBlank
    private long antwoordId;

    protected QuizVraagAntwoord() {
    }

    public QuizVraagAntwoord(long id,
                             @NotBlank long quizVraagId,
                             @NotBlank long antwoordId) {
        this.id = id;
        this.quizVraagId = quizVraagId;
        this.antwoordId = antwoordId;
    }
}
