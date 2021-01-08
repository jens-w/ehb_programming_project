package com.brielage.coursequiz.restresponses;

import com.brielage.coursequiz.domain.Rol;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.List;


public class JsonQuizResponse
        extends JsonResponse {
    private final Rol eigenrol;
    @JsonInclude(Include.NON_NULL)
    private final String vaknaam;
    @JsonInclude(Include.NON_NULL)
    private final List quizlijst;

    public JsonQuizResponse(boolean success,
                            Rol rol,
                            String vaknaam,
                            List quizzes) {
        super(success);
        this.eigenrol = rol;
        this.vaknaam = vaknaam;
        this.quizlijst = quizzes;
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
}
