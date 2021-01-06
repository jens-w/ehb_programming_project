package com.brielage.coursequiz.domain;

public class JsonQuiz {
    private long id, vakid;
    private float hoofdstuknummer;
    private String naam, omschrijving, userkey;
    private boolean isBeschikbaar;

    public JsonQuiz() {
        super();
    }

    public JsonQuiz(long vakid,
                    String naam,
                    String omschrijving) {
        this.vakid = vakid;
        this.naam = naam;
        this.omschrijving = omschrijving;
    }

    public JsonQuiz(long vakid,
                    String naam,
                    String omschrijving,
                    boolean isBeschikbaar) {
        this.vakid = vakid;
        this.naam = naam;
        this.omschrijving = omschrijving;
        this.isBeschikbaar = isBeschikbaar;
    }

    public JsonQuiz(long vakid,
                    String naam,
                    String omschrijving,
                    float hoofdstuknummer,
                    boolean isBeschikbaar) {
        this.vakid = vakid;
        this.naam = naam;
        this.omschrijving = omschrijving;
        this.hoofdstuknummer = hoofdstuknummer;
        this.isBeschikbaar = isBeschikbaar;
    }

    public JsonQuiz(long id,
                    long vakid,
                    String naam,
                    String omschrijving,
                    boolean isBeschikbaar) {
        this.id = id;
        this.vakid = vakid;
        this.naam = naam;
        this.omschrijving = omschrijving;
        this.isBeschikbaar = isBeschikbaar;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVakid() {
        return vakid;
    }

    public void setVakid(long vakid) {
        this.vakid = vakid;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public String getUserkey() {
        return userkey;
    }

    public float getHoofdstuknummer() {
        return hoofdstuknummer;
    }

    public void setHoofdstuknummer(float hoofdstuknummer) {
        this.hoofdstuknummer = hoofdstuknummer;
    }

    public boolean isBeschikbaar() {
        return isBeschikbaar;
    }

    public void setBeschikbaar(boolean beschikbaar) {
        isBeschikbaar = beschikbaar;
    }

    public boolean checkNaam() {
        Validator validator = new Validator();

        if (this.naam == null || this.naam.isBlank()) return false;
        return validator.validateName(this.naam);
    }

    @Override
    public String toString() {
        return id + " " +
                naam + " " +
                omschrijving + " " +
                isBeschikbaar + " " +
                vakid + " " +
                hoofdstuknummer;
    }
}
