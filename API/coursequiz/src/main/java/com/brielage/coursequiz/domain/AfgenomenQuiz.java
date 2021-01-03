package com.brielage.coursequiz.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@SuppressWarnings("unused")
@Entity
@Table(name = "afgenomenquizzen")
public class AfgenomenQuiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private long quizId;
    @NotBlank
    private long studentId;
    @NotBlank
    private LocalDateTime timestamp;
    @NotBlank
    private BigDecimal score;

    protected AfgenomenQuiz() {
    }

    public AfgenomenQuiz(long id,
                         @NotBlank long quizId,
                         @NotBlank long studentId,
                         @NotBlank LocalDateTime timestamp,
                         @NotBlank BigDecimal score) {
        this.id = id;
        this.quizId = quizId;
        this.studentId = studentId;
        this.timestamp = timestamp;
        this.score = score;
    }
}
