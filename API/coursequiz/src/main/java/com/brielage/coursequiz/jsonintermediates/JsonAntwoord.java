package com.brielage.coursequiz.jsonintermediates;

@SuppressWarnings("unused")
public class JsonAntwoord {
    private String userkey, antwoord;
    private long vraagid;
    private boolean isJuist;

    public JsonAntwoord() {
        super();
    }

    public JsonAntwoord(String antwoord,
                        boolean isJuist) {
        this.antwoord = antwoord;
        this.isJuist = isJuist;
    }

    public String getUserkey() {
        return userkey;
    }

    public String getAntwoord() {
        return antwoord;
    }

    public long getVraagid() {
        return vraagid;
    }

    public void setVraagid(long vraagid) {
        this.vraagid = vraagid;
    }

    public boolean isJuist() {
        return isJuist;
    }
}
