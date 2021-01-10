package com.brielage.coursequiz.restresponses;

import com.brielage.coursequiz.domain.Rol;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.List;


@SuppressWarnings({"rawtypes", "unused"})
public class JsonQuizResponse
        extends JsonResponse {
    private final Rol eigenrol;
    @JsonInclude(Include.NON_NULL)
    private String vaknaam;
    @JsonInclude(Include.NON_NULL)
    private List quizlijst;
    @JsonInclude(Include.NON_NULL)
    private long quizid;
    @JsonInclude(Include.NON_NULL)
    private List vragenlijst;
    @JsonInclude(Include.NON_NULL)
    private List vragen_bestaan_niet;
    @JsonInclude(Include.NON_NULL)
    private List antwoorden_bestaan_niet;
    @JsonInclude(Include.NON_NULL)
    private double score;
    @JsonInclude(Include.NON_NULL)
    private double max;

    public JsonQuizResponse(boolean success,
                            Rol eigenrol,
                            String vaknaam,
                            List quizlijst) {
        super(success);
        this.eigenrol = eigenrol;
        this.vaknaam = vaknaam;
        this.quizlijst = quizlijst;
    }

    public JsonQuizResponse(boolean success,
                            Rol eigenrol,
                            long quizid,
                            List vragenlijst) {
        super(success);
        this.eigenrol = eigenrol;
        this.vragenlijst = vragenlijst;
        this.quizid = quizid;
    }

    public JsonQuizResponse(boolean success,
                            Rol eigenrol,
                            List vragen_bestaan_niet,
                            List antwoorden_bestaan_niet) {
        super(success);
        this.eigenrol = eigenrol;

        if (vragen_bestaan_niet.isEmpty()) this.vragen_bestaan_niet = null;
        else this.vragen_bestaan_niet = vragen_bestaan_niet;

        if (antwoorden_bestaan_niet.isEmpty()) this.antwoorden_bestaan_niet = null;
        this.antwoorden_bestaan_niet = antwoorden_bestaan_niet;
    }

    public JsonQuizResponse(boolean success,
                            Rol eigenrol,
                            long quizid,
                            double score,
                            double max) {
        super(success);
        this.eigenrol = eigenrol;
        this.quizid = quizid;
        this.score = score;
        this.max = max;
    }

    public Rol getEigenrol() {
        return eigenrol;
    }

    public String getVaknaam() {
        return vaknaam;
    }

    public List getQuizlijst() {
        return quizlijst;
    }

    public List getVragenlijst() {
        return vragenlijst;
    }

    public long getQuizid() {
        return quizid;
    }

    public List getVragen_bestaan_niet() {
        return vragen_bestaan_niet;
    }

    public List getAntwoorden_bestaan_niet() {
        return antwoorden_bestaan_niet;
    }

    public double getScore() {
        return score;
    }

    public double getMax() {
        return max;
    }
}
