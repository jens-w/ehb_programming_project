package com.brielage.coursequiz.domain;

import javax.persistence.*;
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
    @NotBlank
    @Column
    private String userkey;
    @NotBlank
    @Column
    private String avatarPad;

    protected User() {
    }

    public User(long id,
                @NotBlank String voornaam,
                @NotBlank String familienaam,
                @NotBlank String email,
                @NotBlank String userkey,
                String avatarPad) {
        this.id = id;
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.email = email;
        this.userkey = userkey;
        this.avatarPad = avatarPad;
    }

    @Override
    public String toString() {
        return this.id + " " +
                this.voornaam + " " +
                this.familienaam + " " +
                this.familienaam + " " +
                this.email + " " +
                this.userkey + " " +
                this.avatarPad;
    }
}
