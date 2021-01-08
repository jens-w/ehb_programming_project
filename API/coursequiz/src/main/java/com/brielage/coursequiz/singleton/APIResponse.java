package com.brielage.coursequiz.singleton;

import com.brielage.coursequiz.domain.Rol;
import com.brielage.coursequiz.restresponses.JsonQuizResponse;
import com.brielage.coursequiz.restresponses.JsonResponse;
import com.brielage.coursequiz.restresponses.JsonUserResponse;
import com.brielage.coursequiz.restresponses.JsonVakResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"rawtypes", "unchecked"})
public enum APIResponse {
    INSTANCE;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String respond(boolean success)
            throws JsonProcessingException {
        if (success) return INSTANCE.output(new JsonResponse(true));
        return INSTANCE.output(new JsonResponse(false));
    }

    public static String respond(boolean success,
                                 String reden)
            throws JsonProcessingException {
        if (success) return INSTANCE.output(new JsonResponse(true));
        if (reden.isEmpty()) return INSTANCE.output(new JsonResponse(false));

        Map fouten = new LinkedHashMap();
        fouten.put(reden, true);

        return INSTANCE.output(new JsonResponse(false, fouten));
    }

    public static String respond(boolean success,
                                 List<String> redenen)
            throws JsonProcessingException {
        if (success) return INSTANCE.output(new JsonResponse(true));
        if (redenen.isEmpty()) return INSTANCE.output(new JsonResponse(false));

        Map fouten = new LinkedHashMap();

        for (String reden : redenen) {
            fouten.put(reden, true);
        }

        return INSTANCE.output(new JsonResponse(false, fouten));
    }

    public static String respond(boolean success,
                                 String reden,
                                 String veld)
            throws JsonProcessingException {
        if (success) return INSTANCE.output(new JsonResponse(true));
        if (reden.isEmpty()) return INSTANCE.output(new JsonResponse(false));
        if (veld.isEmpty()) return respond(false, "andere");

        Map fouten = new LinkedHashMap();

        fouten.put(reden, veld);

        return INSTANCE.output(new JsonResponse(false, fouten));
    }

    public static String respond(boolean success,
                                 Rol eigenrol)
            throws JsonProcessingException {
        if (success) return INSTANCE.output(new JsonResponse(true, eigenrol));

        return INSTANCE.output(new JsonResponse(false, eigenrol));
    }

    public static String respond(boolean success,
                                 Map data)
            throws JsonProcessingException {
        if (success) return INSTANCE.output(new JsonResponse(true, data));

        return INSTANCE.output(new JsonResponse(false, data));
    }

    public static String respondUser(boolean success,
                                     Map userdata,
                                     Rol rol)
            throws JsonProcessingException {
        if (success)
            return INSTANCE.output(new JsonUserResponse(true,
                    (String) userdata.get("voornaam"),
                    (String) userdata.get("familienaam"),
                    (String) userdata.get("email"),
                    (String) userdata.get("avatarpad"),
                    rol));

        return INSTANCE.output(new JsonResponse(false));
    }

    public static String respondUser(boolean success,
                                     List users,
                                     Rol eigenrol)
            throws JsonProcessingException {
        return INSTANCE.output(new JsonResponse(success, users, eigenrol));
    }

    public static String respondUser(boolean success,
                                     List users,
                                     Rol eigenrol,
                                     Rol rol)
            throws JsonProcessingException {
        return INSTANCE.output(new JsonResponse(success, users, eigenrol, rol));
    }

    public static String respondUser(boolean success,
                                     List docenten,
                                     List studenten,
                                     List users,
                                     Rol eigenrol)
            throws JsonProcessingException {
        return INSTANCE.output(new JsonResponse(success, docenten, studenten, users, eigenrol));
    }

    public static String respondUser(boolean success,
                                     List admins,
                                     List docenten,
                                     List studenten,
                                     List users,
                                     Rol eigenrol)
            throws JsonProcessingException {
        return INSTANCE.output(new JsonResponse(success, admins, docenten, studenten, users, eigenrol));
    }

    public static String respondVak(boolean success,
                                    Rol eigenrol,
                                    List vakken)
            throws JsonProcessingException {
        if (success) return INSTANCE.output(new JsonVakResponse(true, eigenrol, vakken));

        return INSTANCE.output(new JsonResponse(false));
    }

    public static String respondQuiz(boolean success,
                                     Rol eigenrol,
                                     String vaknaam,
                                     List quizList)
            throws JsonProcessingException {
        if (success)
            return INSTANCE.output(new JsonQuizResponse(true, eigenrol, vaknaam, quizList));

        return INSTANCE.output(new JsonResponse(false));
    }

    public String output(JsonResponse jsonResponse) throws JsonProcessingException {
        // LOG
        ResponseLogger.logJsonResponse(jsonResponse);

        return objectMapper.writeValueAsString(jsonResponse);
    }
}
