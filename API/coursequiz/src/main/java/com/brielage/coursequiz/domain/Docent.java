package com.brielage.coursequiz.domain;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@SuppressWarnings("unused")
@Entity
@Table(name = "docenten")
@PrimaryKeyJoinColumn(name = "userid")
public class Docent extends User {
    protected Docent() {
    }

    public Docent(@NotBlank String voornaam,
                  @NotBlank String familienaam,
                  @NotBlank String email,
                  @NotBlank String userkey,
                  String avatarPad) {
        super(
                voornaam,
                familienaam,
                email,
                userkey,
                avatarPad
        );
    }
}
