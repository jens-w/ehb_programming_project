package com.brielage.coursequiz.restservices;

import com.brielage.coursequiz.domain.APIResponse;
import com.brielage.coursequiz.domain.JsonOpleiding;
import com.brielage.coursequiz.domain.Opleiding;
import com.brielage.coursequiz.domain.Rol;
import com.brielage.coursequiz.domain.User;
import com.brielage.coursequiz.domain.UserRol;
import com.brielage.coursequiz.services.OpleidingService;
import com.brielage.coursequiz.services.StudentService;
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

    public String createOpleiding(JsonNode jsonNode) throws JsonProcessingException {
        // LOG
        logRequest(jsonNode.toPrettyString());

        try {
            JsonOpleiding jsonOpleiding = objectMapper.treeToValue(jsonNode, JsonOpleiding.class);
            Map fouten = new LinkedHashMap();

            // LOG
            logger.info("\njsonOpleiding:" + jsonOpleiding.toString());

            //TODO validate name?

            if (!doesUserHaveRights(jsonOpleiding.getUserkey()))
                return APIResponse.respond(false, "rechten_ongeldig");

            if (jsonOpleiding.getOpleidingnaam().isEmpty())
                return APIResponse.respond(false, "opleidingnaam_leeg");

            if (doesOpleidingExist(0, jsonOpleiding.getOpleidingnaam()))
                return APIResponse.respond(false, "opleidingnaam_bestaat_al");

            Opleiding opleiding = new Opleiding(jsonOpleiding.getOpleidingnaam());
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

    public String removeOpleiding(JsonNode jsonNode) throws JsonProcessingException {
        // LOG
        logRequest(jsonNode.toPrettyString());

        try {
            JsonOpleiding jsonOpleiding = objectMapper.treeToValue(jsonNode, JsonOpleiding.class);

            // LOG
            logger.info("\njsonOpleiding:" + jsonOpleiding.toString());

            if (!doesUserHaveRights(jsonOpleiding.getUserkey()))
                return APIResponse.respond(false, "rechten_ongeldig");

            Optional<Opleiding> optionalOpleiding = opleidingService.findById(jsonOpleiding.getId());

            Map errors = new LinkedHashMap();

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

    public void logRequest(String s) {
        logger.info("\nrequest:\n" + s);
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean doesUserHaveRights(String userkey) {
        List<User> userListByUserkey = userService.findByUserkey(userkey);
        if (userListByUserkey.size() != 1) return false;

        Optional<UserRol> optionalUserRol = userRolService.findByUserId(userListByUserkey.get(0).getId());
        if (optionalUserRol.isEmpty()) return false;

        Rol rol = optionalUserRol.get().getRol();

        return rol == Rol.ADMIN || rol == Rol.DOCENT;
    }

    public boolean doesOpleidingExist(long id, String opleidingnaam) {
        if (id > 0) {
            Optional<Opleiding> optionalOpleiding = opleidingService.findById(id);
            return optionalOpleiding.isPresent();
        } else {
            List<Opleiding> opleidingListByNaam = opleidingService.findByNaam(opleidingnaam);
            return opleidingListByNaam.size() > 0;
        }
    }
}
