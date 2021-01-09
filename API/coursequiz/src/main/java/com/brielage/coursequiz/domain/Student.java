package com.brielage.coursequiz.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@SuppressWarnings("unused")
@Entity
@Table(name = "studenten")
@PrimaryKeyJoinColumn(name = "userid")
public class Student extends User {
    @Column
    private long opleidingid;

    protected Student() {
    }

    public Student(long id,
                   String voornaam,
                   String familienaam,
                   String email,
                   String userkey,
                   String avatarpad,
                   String password,
                   long opleidingid) {
        super(id, voornaam, familienaam, email, userkey, avatarpad, password);
        this.opleidingid = opleidingid;
    }

    public Student(long userid,
                   long opleidingid) {
        this.opleidingid = opleidingid;
    }

    public Student(String voornaam,
                   String familienaam,
                   String email,
                   String userkey,
                   String avatarpad,
                   long opleidingid) {
        super(
                voornaam,
                familienaam,
                email,
                userkey,
                avatarpad
        );
        this.opleidingid = opleidingid;
    }

    @Override
    public String toString() {
        return super.toString() + " " +
                this.opleidingid;
    }

    public long getOpleidingid() {
        return opleidingid;
    }

    public void setOpleidingid(long opleidingid) {
        this.opleidingid = opleidingid;
    }
}
