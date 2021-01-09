package com.brielage.coursequiz.restresponses;

import com.brielage.coursequiz.domain.Rol;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.List;

public class JsonVakResponse
        extends JsonResponse {
    private final Rol rol;
    @JsonInclude(Include.NON_NULL)
    private final List vakken;

    public JsonVakResponse(boolean success,
                           Rol rol,
                           List vakken) {
        super(success);
        this.rol = rol;
        this.vakken = vakken;
    }

    public List getVakken() {
        return vakken;
    }

    public Rol getRol() {
        return rol;
    }
}
