package com.brielage.coursequiz.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@SuppressWarnings("unused")
@Entity
@Table(name = "quizvragen")
public class QuizVraag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private long quizId;
    @NotNull
    private long vraagId;

    protected QuizVraag() {
    }

    public QuizVraag(long id,
                     @NotNull long quizId,
                     @NotNull long vraagId) {
        this.id = id;
        this.quizId = quizId;
        this.vraagId = vraagId;
    }

    public QuizVraag(@NotNull long quizId,
                     @NotNull long vraagId) {
        this.quizId = quizId;
        this.vraagId = vraagId;
    }

    public long getQuizId() {
        return quizId;
    }

    public long getVraagId() {
        return vraagId;
    }

    @Override
    public String toString() {
        return "QuizVraag{" +
                "id=" + id +
                ", quizId=" + quizId +
                ", vraagId=" + vraagId +
                '}';
    }
}
