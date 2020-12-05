package com.brielage.coursequiz.restservices;

import com.brielage.coursequiz.domain.JsonResponse;
import com.brielage.coursequiz.domain.User;
import com.brielage.coursequiz.domain.Validator;
import com.brielage.coursequiz.services.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class UserRestService {
    private final UserService userService;
    private final Validator validator = new Validator();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String defaultAvatarPad = "\\public\\images\\account\\accountinfo\\avatar_default.png";

    public UserRestService(UserService userService) {
        this.userService = userService;
    }


    @SuppressWarnings("rawtypes")
    public Map createUser(JsonNode jsonNode) {
        /*
            REQUEST
            {
                "voornaam" : "mijn_voornaam",
                "familienaam" : "mijn_familienaam",
                "email" : "mijn@ema.il",
                "password" : "mijn_password12!34"
            }

            RESPONSE SUCCESS
            {
                "success" : true
            }

            RESPONSE FAIL
            {
                "success" : false,
                "error" : [
                    "voornaam_ongeldig" : true,
                    "familienaam_ongeldig" : true,
                    "email_ongeldig" : true,
                    "email_bestaat_al" : true,
                    "password_ongeldig" : true
                ]
            }
         */

        JsonResponse jsonResponse = null;
        String voornaam = "";
        String familienaam = "";
        String email = "";
        String password = "";

        Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();

        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            String key = entry.getKey();
            String value = entry.getValue().asText();

            switch (key) {
                case "voornaam" -> voornaam = value;
                case "familienaam" -> familienaam = value;
                case "email" -> email = value;
                case "password" -> password = value;
            }
        }

        boolean voornaamGeldig = validator.validateName(voornaam);
        boolean familienaamGeldig = validator.validateName(familienaam);
        boolean emailGeldig = validator.validateEmail(email);
        boolean passwordGeldig = validator.validatePassword(password);

        if (!voornaamGeldig ||
                !familienaamGeldig ||
                !emailGeldig ||
                !passwordGeldig) {
            jsonResponse = new JsonResponse(false);
            LinkedHashMap fouten = new LinkedHashMap();

            if (!voornaamGeldig) fouten.put("voornaam_ongeldig", true);
            if (!familienaamGeldig) fouten.put("familienaam_ongeldig", true);
            if (!emailGeldig) fouten.put("email_ongeldig", true);
            if (!passwordGeldig) fouten.put("password_ongeldig", true);

            jsonResponse.add("errors", fouten);
        } else {
            User u = new User(voornaam, familienaam, email, "");
            u.setAvatarPad(defaultAvatarPad);
            logger.info("\nuser info:");
            logger.info(u.toString());
            logger.info("\n");

            try {
                userService.create(u);
                jsonResponse = new JsonResponse(true);
                return jsonResponse.getContent();
            } catch (Exception e) {
                logger.error(e.getMessage());
            }

        }

        //noinspection ConstantConditions

        logger.info("\n");
        logger.info(jsonResponse.getContent().toString());

        return jsonResponse.getContent();
    }
}
