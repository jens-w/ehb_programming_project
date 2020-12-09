package com.brielage.coursequiz.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@SuppressWarnings("unused")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;
    @NotBlank
    @Column
    private String voornaam;
    @NotBlank
    @Column
    private String familienaam;
    @NotBlank
    @Column
    private String email;
    @Column
    private String userkey;
    @Column
    private String avatarPad;
    @Column
    private String password;

    protected User() {
    }

    public User(@NotBlank String voornaam,
                @NotBlank String familienaam,
                @NotBlank String email,
                String avatarPad,
                String password) {
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.email = email;
        this.avatarPad = avatarPad;
        this.password = password;
    }

    public User(@NotBlank String voornaam,
                @NotBlank String familienaam,
                @NotBlank String email,
                String userkey,
                String avatarPad,
                String password) {
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.email = email;
        this.userkey = userkey;
        this.avatarPad = avatarPad;
        this.password = password;
    }

    @Override
    public String toString() {
        return this.id + " " +
                this.voornaam + " " +
                this.familienaam + " " +
                this.email + " " +
                this.userkey + " " +
                this.avatarPad;
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

    public void generateUserkey() {
        this.userkey = "userkey1234";
    }

    public String getAvatarPad() {
        return avatarPad;
    }

    public void setAvatarPad(String avatarPad) {
        this.avatarPad = avatarPad;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
