package com.brielage.coursequiz.restresponses;

import com.brielage.coursequiz.domain.Rol;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.List;
import java.util.Map;

@SuppressWarnings({"rawtypes", "unused", "FieldCanBeLocal"})
public class JsonUserResponse
        extends JsonResponse {
    @JsonInclude(Include.NON_NULL)
    private final String userkey, voornaam, familienaam, email, avatarpad;
    @JsonInclude(Include.NON_NULL)
    private Map opleiding;
    @JsonInclude(Include.NON_NULL)
    private List vakken;
    private Rol rol;

    public JsonUserResponse(boolean success,
                            String userkey,
                            String voornaam,
                            String familienaam,
                            String email,
                            String avatarpad) {
        super(success);
        this.userkey = userkey;
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.email = email;
        this.avatarpad = avatarpad;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public void setVakken(List vakken) {
        this.vakken = vakken;
    }

    public void setOpleiding(Map opleiding) {
        this.opleiding = opleiding;
    }

    public String getUserkey() {
        return userkey;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatarpad() {
        return avatarpad;
    }

    public List getVakken() {
        return vakken;
    }

    public Map getOpleiding() {
        return opleiding;
    }

    public Rol getRol() {
        return rol;
    }
}
