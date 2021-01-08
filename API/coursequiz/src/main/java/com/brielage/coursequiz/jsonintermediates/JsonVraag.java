package com.brielage.coursequiz.jsonintermediates;

import java.util.List;

public class JsonVraag {
    private String userkey, vraag;
    long aantal_antwoorden_tonen, quizid, minimum_aantal_juiste_antwoorden_tonen,
            aantal_juiste_antwoorden_nodig, hoofdstukid;
    boolean simpele_vraag, juiste_antwoord_tonen;
    List antwoorden;

    public JsonVraag() {
        super();
    }

    public String getUserkey() {
        return userkey;
    }

    public String getVraag() {
        return vraag;
    }

    public long getAantal_antwoorden_tonen() {
        return aantal_antwoorden_tonen;
    }

    public long getQuizid() {
        return quizid;
    }

    public long getMinimum_aantal_juiste_antwoorden_tonen() {
        return minimum_aantal_juiste_antwoorden_tonen;
    }

    public long getAantal_juiste_antwoorden_nodig() {
        return aantal_juiste_antwoorden_nodig;
    }

    public long getHoofdstukid() {
        return hoofdstukid;
    }

    public boolean isSimpele_vraag() {
        return simpele_vraag;
    }

    public boolean isJuiste_antwoord_tonen() {
        return juiste_antwoord_tonen;
    }

    public List getAntwoorden() {
        return antwoorden;
    }

    @Override
    public String toString() {
        return "/" + vraag + "/" +
                aantal_antwoorden_tonen + "/" +
                quizid + "/" +
                simpele_vraag + "/" +
                juiste_antwoord_tonen + "/" +
                minimum_aantal_juiste_antwoorden_tonen + "/" +
                aantal_juiste_antwoorden_nodig + "/" +
                hoofdstukid + "/" +
                antwoorden;
    }
}
