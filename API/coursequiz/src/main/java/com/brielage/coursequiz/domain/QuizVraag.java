package com.brielage.coursequiz.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@SuppressWarnings("unused")
@Entity
@Table(name = "quizvragen")
public class QuizVraag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private long quizId;
    @NotBlank
    private long vraagId;

    protected QuizVraag() {
    }

    public QuizVraag(long id,
                     @NotBlank long quizId,
                     @NotBlank long vraagId) {
        this.id = id;
        this.quizId = quizId;
        this.vraagId = vraagId;
    }
}
