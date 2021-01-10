package com.brielage.coursequiz.restservices;

import com.brielage.coursequiz.domain.Opleiding;
import com.brielage.coursequiz.domain.User;
import com.brielage.coursequiz.jsonintermediates.JsonOpleiding;
import com.brielage.coursequiz.services.OpleidingService;
import com.brielage.coursequiz.services.StudentService;
import com.brielage.coursequiz.services.UserRolService;
import com.brielage.coursequiz.services.UserService;
import com.brielage.coursequiz.singleton.APIResponse;
import com.brielage.coursequiz.singleton.ResponseLogger;
import com.brielage.coursequiz.singleton.Tools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@SuppressWarnings({"DuplicatedCode"})
public class OpleidingRestService {
    private final UserService userService;
    private final UserRolService userRolService;
    private final OpleidingService opleidingService;
    private final StudentService studentService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public OpleidingRestService(UserService userService,
                                UserRolService userRolService,
                                OpleidingService opleidingService,
                                StudentService studentService) {
        this.userService = userService;
        this.userRolService = userRolService;
        this.opleidingService = opleidingService;
        this.studentService = studentService;
    }

    public String createOpleiding(JsonNode jsonNode)
            throws JsonProcessingException {
        // LOG
        ResponseLogger.logRequest("opleiding.create", jsonNode.toPrettyString());

        try {
            JsonOpleiding jsonOpleiding = objectMapper.treeToValue(jsonNode, JsonOpleiding.class);

            //TODO validate name?

            if (!Tools.userExists(jsonOpleiding.getUserkey(), userService))
                return APIResponse.respond(false, "andere");

            User u = userService.findByUserkey(jsonOpleiding.getUserkey()).get(0);

            if (!Tools.isAdminOrDocent(u, userRolService))
                return APIResponse.respond(false, "rechten_ongeldig");

            String opleidingNaam = jsonOpleiding.getOpleidingnaam();

            if (opleidingNaam.isEmpty())
                return APIResponse.respond(false, "opleidingnaam_leeg");

            if (Tools.doesOpleidingExist(0, opleidingNaam, opleidingService))
                return APIResponse.respond(false, "opleidingnaam_bestaat_al");

            Opleiding opleiding = new Opleiding(opleidingNaam);
            opleidingService.create(opleiding);

            return APIResponse.respond(true);
        } catch (UnrecognizedPropertyException e) {
            e.printStackTrace();

            // LOG
            logger.error("\n" + e.getMessage());

            return APIResponse.respond(false, "veld_ongeldig");
        } catch (JsonProcessingException e) {
            e.printStackTrace();

            // LOG
            logger.error("\n" + e.getMessage());

            return APIResponse.respond(false, "andere");
        }
    }

    public String removeOpleiding(JsonNode jsonNode)
            throws JsonProcessingException {
        // LOG
        ResponseLogger.logRequest("opleiding.remove", jsonNode.toPrettyString());

        try {
            JsonOpleiding jsonOpleiding = objectMapper.treeToValue(jsonNode, JsonOpleiding.class);

            if (!Tools.userExists(jsonOpleiding.getUserkey(), userService))
                return APIResponse.respond(false, "andere");

            User u = userService.findByUserkey(jsonOpleiding.getUserkey()).get(0);

            if (!Tools.isAdminOrDocent(u, userRolService))
                return APIResponse.respond(false, "rechten_ongeldig");

            Optional<Opleiding> optionalOpleiding = opleidingService.findById(jsonOpleiding.getId());

            if (optionalOpleiding.isEmpty())
                return APIResponse.respond(false, "opleiding_id_bestaat_niet");

            Opleiding opleiding = optionalOpleiding.get();

            if (!studentService.findByOpleidingId(opleiding.getId()).isEmpty())
                return APIResponse.respond(false, "opleiding_heeft_nog_studenten");

            try {
                opleidingService.remove(opleiding);
                return APIResponse.respond(true);
            } catch (Exception e) {
                e.printStackTrace();

                // LOG
                logger.error("\n" + e.getMessage());

                return APIResponse.respond(false, "andere");
            }
        } catch (UnrecognizedPropertyException e) {
            e.printStackTrace();

            // LOG
            logger.error("\n" + e.getMessage());

            return APIResponse.respond(false, "veld_ongeldig");
        } catch (JsonProcessingException e) {
            e.printStackTrace();

            // LOG
            logger.error("\n" + e.getMessage());

            return APIResponse.respond(false, "andere");
        }
    }
}
