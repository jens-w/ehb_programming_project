package com.brielage.coursequiz.restresponses;

import com.brielage.coursequiz.domain.Rol;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.Map;

@SuppressWarnings({"rawtypes", "unused"})
public class JsonResponse {
    private final boolean success;
    @JsonInclude(Include.NON_NULL)
    private Map errors;
    @JsonInclude(Include.NON_NULL)
    private Rol eigenrol;

    public JsonResponse(boolean success) {
        this.success = success;
    }

    public JsonResponse(boolean success,
                        Rol eigenrol) {
        this.success = success;
        this.eigenrol = eigenrol;
    }

    public JsonResponse(boolean success,
                        Map errors) {
        this.success = success;
        this.errors = errors;
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
}
