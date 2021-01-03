package com.brielage.coursequiz.domain;

public class Validator {
    public boolean validateName(String name) {
        if (name.equals("null")) return false;
        return name.matches("(?i)[a-z]([- ',.a-z]{0,48}[a-z])?");
    }

    public boolean validateEmail(String email) {
        if (email.equals("null")) return false;
        return email.matches(".+@.+\\..+");
    }

    public boolean validatePassword(String password) {
        if (password.equals("null")) return false;
        return password.length() >= 8;
    }
}
