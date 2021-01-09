package com.brielage.coursequiz.restresponses;

import com.brielage.coursequiz.domain.Rol;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.List;
import java.util.Map;

@SuppressWarnings({"rawtypes", "unused"})
public class JsonResponse {
    private final boolean success;
    @JsonInclude(Include.NON_NULL)
    private Map errors;
    @JsonInclude(Include.NON_NULL)
    private Map data;
    @JsonInclude(Include.NON_NULL)
    private Rol eigenrol;
    @JsonInclude(Include.NON_NULL)
    private List admins;
    @JsonInclude(Include.NON_NULL)
    private List docenten;
    @JsonInclude(Include.NON_NULL)
    private List studenten;
    @JsonInclude(Include.NON_NULL)
    private List users;

    public JsonResponse(boolean success) {
        this.success = success;
    }

    public JsonResponse(boolean success,
                        Rol eigenrol) {
        this.success = success;
        this.eigenrol = eigenrol;
    }

    public JsonResponse(boolean success,
                        Map m) {
        this.success = success;
        if (success) this.data = m;
        else this.errors = m;
    }

    public JsonResponse(boolean success,
                        List users,
                        Rol eigenrol) {
        this.success = success;
        this.users = users;
        this.eigenrol = eigenrol;
    }

    public JsonResponse(boolean success,
                        List users,
                        Rol eigenrol,
                        Rol rol) {
        this.success = success;
        if (rol == Rol.ADMIN) this.admins = users;
        else if (rol == Rol.DOCENT) this.docenten = users;
        else if (rol == Rol.STUDENT) this.studenten = users;
        else if (rol == Rol.USER) this.users = users;
        this.eigenrol = eigenrol;
    }

    public JsonResponse(boolean success,
                        List docenten,
                        List studenten,
                        List users,
                        Rol eigenrol) {
        this.success = success;
        this.docenten = docenten;
        this.studenten = studenten;
        this.users = users;
        this.eigenrol = eigenrol;
    }

    public JsonResponse(boolean success,
                        List admins,
                        List docenten,
                        List studenten,
                        List users,
                        Rol eigenrol) {
        this.success = success;
        this.admins = admins;
        this.docenten = docenten;
        this.studenten = studenten;
        this.users = users;
        this.eigenrol = eigenrol;
    }

    public boolean isSuccess() {
        return success;
    }

    public Rol getEigenrol() {
        return eigenrol;
    }

    public Map getErrors() {
        return errors;
    }

    public Map getData() {
        return data;
    }

    public List getUsers() {
        return users;
    }

    public List getAdmins() {
        return admins;
    }

    public List getDocenten() {
        return docenten;
    }

    public List getStudenten() {
        return studenten;
    }
}
