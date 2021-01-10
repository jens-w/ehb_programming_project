package com.brielage.coursequiz.restresponses;

import com.brielage.coursequiz.domain.Rol;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.List;

@SuppressWarnings({"rawtypes", "unused"})
public class JsonVakResponse
        extends JsonResponse {
    private final Rol eigenrol;
    @JsonInclude(Include.NON_NULL)
    private final List vakken;

    public JsonVakResponse(boolean success,
                           Rol eigenrol,
                           List vakken) {
        super(success);
        this.eigenrol = eigenrol;
        this.vakken = vakken;
    }

    public List getVakken() {
        return vakken;
    }

    public Rol getEigenrol() {
        return eigenrol;
    }
}
