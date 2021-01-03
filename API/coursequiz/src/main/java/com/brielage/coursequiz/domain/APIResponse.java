package com.brielage.coursequiz.domain;

import com.brielage.coursequiz.restresponses.JsonResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"rawtypes", "unchecked"})
public enum APIResponse {
    INSTANCE;

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static JsonResponse jsonResponse;

    public static String respond(boolean success) throws JsonProcessingException {
        if (success) return INSTANCE.output(new JsonResponse(true));
        return INSTANCE.output(new JsonResponse(false));
    }

    public static String respond(boolean success, String reden) throws JsonProcessingException {
        if (success) return INSTANCE.output(new JsonResponse(true));
        if (reden.isEmpty()) return INSTANCE.output(new JsonResponse(false));

        Map fouten = new LinkedHashMap();
        fouten.put(reden, true);

        return INSTANCE.output(new JsonResponse(false, fouten));
    }

    public static String respond(boolean success, List<String> redenen) throws JsonProcessingException {
        if (success) return INSTANCE.output(new JsonResponse(true));
        if (redenen.isEmpty()) return INSTANCE.output(new JsonResponse(false));

        Map fouten = new LinkedHashMap();

        for (String reden : redenen) {
            fouten.put(reden, true);
        }

        return INSTANCE.output(new JsonResponse(false, fouten));
    }

    public static String respond(boolean success, String reden, String veld) throws JsonProcessingException {
        if (success) return INSTANCE.output(new JsonResponse(true));
        if (reden.isEmpty()) return INSTANCE.output(new JsonResponse(false));
        if (veld.isEmpty()) return respond(false, "andere");

        Map fouten = new LinkedHashMap();

        fouten.put(reden, veld);

        return INSTANCE.output(new JsonResponse(false, fouten));
    }

    public String output(JsonResponse jsonResponse) throws JsonProcessingException {
        // LOG
        ResponseLogger.logJsonResponse(jsonResponse);

        return objectMapper.writeValueAsString(jsonResponse);
    }
}
