package com.brielage.coursequiz.jsonintermediates;

@SuppressWarnings("unused")
public class JsonOpleiding {
    private long id;
    private String opleidingnaam, userkey;

    public JsonOpleiding() {
        super();
    }

    public JsonOpleiding(String opleidingnaam) {
        this.opleidingnaam = opleidingnaam;
    }

    public JsonOpleiding(long id, String opleidingnaam) {
        this.id = id;
        this.opleidingnaam = opleidingnaam;
    }

    public long getId() {
        return id;
    }

    public String getOpleidingnaam() {
        return opleidingnaam;
    }

    public void setOpleidingnaam(String opleidingnaam) {
        this.opleidingnaam = opleidingnaam;
    }

    public String getUserkey() {
        return userkey;
    }

    @Override
    public String toString() {
        return id + " " + opleidingnaam;
    }
}
