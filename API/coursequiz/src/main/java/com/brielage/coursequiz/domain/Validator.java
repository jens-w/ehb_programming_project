package com.brielage.coursequiz.domain;

public class Validator {
    public boolean validateName(String name) {
        return name.matches("(?i)[a-z]([- ',.a-z]{0,48}[a-z])?");
    }

    public boolean validateEmail(String email) {
        return email.matches(".+@.+\\..+");
    }

    public boolean validatePassword(String password) {
        return password.length() >= 8;
    }
}
