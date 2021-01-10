package com.brielage.coursequiz.jsonintermediates;

import java.util.List;
import java.util.Map;

@SuppressWarnings({"rawtypes", "unused"})
public class JsonQuizSubmission {
    private long quizid;
    private String userkey;
    private List<Map> antwoordenlijst;

    public JsonQuizSubmission() {
        super();
    }

    public JsonQuizSubmission(long quizid,
                              String userkey,
                              List<Map> antwoordenlijst) {
        this.quizid = quizid;
        this.userkey = userkey;
        this.antwoordenlijst = antwoordenlijst;
    }

    public long getQuizid() {
        return quizid;
    }

    public String getUserkey() {
        return userkey;
    }

    public List<Map> getAntwoordenlijst() {
        return antwoordenlijst;
    }

    @Override
    public String toString() {
        return "JsonQuizSubmission{" +
                "quizid=" + quizid +
                ", antwoordenlijst=" + antwoordenlijst +
                '}';
    }
}
