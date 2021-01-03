package com.brielage.coursequiz.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rollen")
public class UserRol {
    @Id
    private long userId;
    private Rol rol;

    protected UserRol() {
    }

    public UserRol(long userId, Rol rol) {
        this.userId = userId;
        this.rol = rol;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
