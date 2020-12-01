package com.brielage.coursequiz.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@SuppressWarnings("unused")
@Entity
@Table(name = "studenten")
@PrimaryKeyJoinColumn(name = "userid")
public class Student extends User {
    @NotBlank
    @Column
    private long opleidingid;

    protected Student() {
    }

    public Student(@NotBlank long userid,
                   @NotBlank long opleidingid) {
        this.opleidingid = opleidingid;
    }

    public Student(long id,
                   @NotBlank String voornaam,
                   @NotBlank String familienaam,
                   @NotBlank String email,
                   @NotBlank String userkey,
                   String avatarPad,
                   @NotBlank long opleidingid) {
        super(
                id,
                voornaam,
                familienaam,
                email,
                userkey,
                avatarPad
        );
        this.opleidingid = opleidingid;
    }

    @Override
    public String toString() {
        return super.toString() + " " +
                this.opleidingid;
    }
}
