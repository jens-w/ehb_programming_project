package com.brielage.coursequiz.jsonintermediates;

public class JsonVak {
    private long id;
    private String vaknaam, userkey;

    public JsonVak() {
        super();
    }

    public JsonVak(String vaknaam) {
        this.vaknaam = vaknaam;
    }

    public JsonVak(long id, String vaknaam) {
        this.id = id;
        this.vaknaam = vaknaam;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVaknaam() {
        return vaknaam;
    }

    public void setVaknaam(String vaknaam) {
        this.vaknaam = vaknaam;
    }

    public String getUserkey() {
        return userkey;
    }

    public void setUserkey(String userkey) {
        this.userkey = userkey;
    }

    @Override
    public String toString() {
        return id + " " + vaknaam;
    }
}
