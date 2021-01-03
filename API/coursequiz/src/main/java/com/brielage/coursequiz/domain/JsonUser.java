package com.brielage.coursequiz.domain;

public class JsonUser {
    private long id;
    private String voornaam, familienaam, email, userkey, avatarpad, password;
    private Rol rol;

    public JsonUser() {
        super();
    }

    public JsonUser(String voornaam,
                    String familienaam,
                    String email,
                    String password) {
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.email = email;
        this.password = password;
    }

    public JsonUser(String voornaam,
                    String familienaam,
                    String email,
                    String userkey,
                    String avatarpad,
                    String password) {
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.email = email;
        this.userkey = userkey;
        this.avatarpad = avatarpad;
        this.password = password;
    }

    public JsonUser(long id,
                    String voornaam,
                    String familienaam,
                    String email,
                    String userkey,
                    String avatarpad,
                    String password) {
        this.id = id;
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.email = email;
        this.userkey = userkey;
        this.avatarpad = avatarpad;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public void setFamilienaam(String familienaam) {
        this.familienaam = familienaam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserkey() {
        return userkey;
    }

    public void setUserkey(String userkey) {
        this.userkey = userkey;
    }

    public String getAvatarpad() {
        return avatarpad;
    }

    public void setAvatarpad(String avatarpad) {
        this.avatarpad = avatarpad;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean checkVoornaam() {
        Validator validator = new Validator();

        if (this.voornaam == null || this.voornaam.isBlank()) return false;
        return validator.validateName(this.voornaam);
    }

    public boolean checkFamielienaam() {
        Validator validator = new Validator();

        if (this.familienaam == null || this.familienaam.isBlank()) return false;
        return validator.validateName(this.familienaam);
    }

    public boolean checkEmail() {
        Validator validator = new Validator();

        if (this.email == null || this.email.isBlank()) return false;
        return validator.validateEmail(this.email);
    }

    public boolean checkPassword() {
        Validator validator = new Validator();

        if (this.password == null || this.password.isBlank()) return false;
        return validator.validatePassword(this.password);
    }

    public boolean checkAvatarpad() {
        // TODO write logic
        return true;
    }

    @Override
    public String toString() {
        return id + "; " +
                voornaam + "; " +
                familienaam + "; " +
                email + "; " +
                userkey + "; " +
                avatarpad + "; " +
                password;
    }
}
