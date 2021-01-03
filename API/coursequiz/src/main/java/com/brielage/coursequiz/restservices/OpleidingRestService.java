package com.brielage.coursequiz.restservices;

import com.brielage.coursequiz.domain.JsonOpleiding;
import com.brielage.coursequiz.domain.Opleiding;
import com.brielage.coursequiz.domain.ResponseLogger;
import com.brielage.coursequiz.domain.Rol;
import com.brielage.coursequiz.domain.User;
import com.brielage.coursequiz.domain.UserRol;
import com.brielage.coursequiz.restresponses.JsonResponse;
import com.brielage.coursequiz.services.OpleidingService;
import com.brielage.coursequiz.services.UserRolService;
import com.brielage.coursequiz.services.UserService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings({"unchecked", "rawtypes", "DuplicatedCode"})
public class OpleidingRestService {
    private final UserService userService;
    private final UserRolService userRolService;
    private final OpleidingService opleidingService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public OpleidingRestService(UserService userService,
                                UserRolService userRolService,
                                OpleidingService opleidingService) {
        this.userService = userService;
        this.userRolService = userRolService;
        this.opleidingService = opleidingService;
    }

    public String createOpleiding(JsonNode jsonNode) throws JsonProcessingException {
        // LOG
        logger.info("\nrequest:\n" + jsonNode.toPrettyString());

        try {
            JsonOpleiding jsonOpleiding = objectMapper.treeToValue(jsonNode, JsonOpleiding.class);
            Map fouten = new LinkedHashMap();

            // LOG
            logger.info("\njsonOpleiding:" + jsonOpleiding.toString());

            //TODO validate name?

            List<User> userListByUserkey = userService.findByUserkey(jsonOpleiding.getUserkey());

            if (userListByUserkey.size() != 1) {
                fouten.put("andere", true);
                JsonResponse jsonResponse = new JsonResponse(false, fouten);

                // LOG
                ResponseLogger.logJsonResponse(jsonResponse);

                return objectMapper.writeValueAsString(jsonResponse);
            }

            Optional<UserRol> optionalUserRol = userRolService.findByUserId(userListByUserkey.get(0).getId());

            if (optionalUserRol.isEmpty()) {
                fouten.put("andere", true);
                JsonResponse jsonResponse = new JsonResponse(false, fouten);

                // LOG
                ResponseLogger.logJsonResponse(jsonResponse);

                return objectMapper.writeValueAsString(jsonResponse);
            }

            Rol rol = optionalUserRol.get().getRol();

            if (rol != Rol.ADMIN && rol != Rol.DOCENT) {
                fouten.put("rechten_ongeldig", true);
                JsonResponse jsonResponse = new JsonResponse(false, fouten);

                // LOG
                ResponseLogger.logJsonResponse(jsonResponse);

                return objectMapper.writeValueAsString(jsonResponse);
            }

            if (jsonOpleiding.getOpleidingnaam().isEmpty()) {
                fouten.put("opleidingnaam_leeg", true);
                JsonResponse jsonResponse = new JsonResponse(false, fouten);

                // LOG
                ResponseLogger.logJsonResponse(jsonResponse);

                return objectMapper.writeValueAsString(jsonResponse);
            }

            List<Opleiding> opleidingListByNaam = opleidingService.findByNaam(jsonOpleiding.getOpleidingnaam());

            if (opleidingListByNaam.size() > 0) {
                fouten.put("opleidingnaam_bestaat_al", true);
                JsonResponse jsonResponse = new JsonResponse(false, fouten);

                // LOG
                ResponseLogger.logJsonResponse(jsonResponse);

                return objectMapper.writeValueAsString(jsonResponse);
            }

            Opleiding opleiding = new Opleiding(jsonOpleiding.getOpleidingnaam());
            opleidingService.create(opleiding);
            JsonResponse jsonResponse = new JsonResponse(true);

            // LOG
            ResponseLogger.logJsonResponse(jsonResponse);

            return objectMapper.writeValueAsString(jsonResponse);
        } catch (UnrecognizedPropertyException e) {
            e.printStackTrace();

            Map fouten = new LinkedHashMap();
            fouten.put("veld_ongeldig", e.getPropertyName());

            // LOG
            logger.error("\n" + e.getMessage());
            ResponseLogger.logJsonResponse(new JsonResponse(false, fouten));

            return objectMapper.writeValueAsString(new JsonResponse(false, fouten));
        } catch (JsonProcessingException e) {
            e.printStackTrace();

            Map fouten = new LinkedHashMap();
            fouten.put("andere", true);

            // LOG
            logger.info("\n" + e.getMessage());
            ResponseLogger.logJsonResponse(new JsonResponse(false, fouten));

            return objectMapper.writeValueAsString(new JsonResponse(false, fouten));
        }
    }
}
