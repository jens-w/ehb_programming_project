package com.brielage.coursequiz.restresponses;

import com.brielage.coursequiz.domain.Rol;

import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@SuppressWarnings({"rawtypes", "unused", "FieldCanBeLocal"})
public class JsonUserResponse
        extends JsonResponse {
    private final String userkey, voornaam, familienaam, email, avatarPad;
    private List vakken;
    @JsonInclude(Include.NON_NULL)
    private Map opleiding;
    private Rol rol;

    public JsonUserResponse(boolean success,
                            String userkey,
                            String voornaam,
                            String familienaam,
                            String email,
                            String avatarPad) {
        super(success);
        this.userkey = userkey;
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.email = email;
        this.avatarPad = avatarPad;
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

    public String getAvatarPad() {
        return avatarPad;
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
