package com.brielage.coursequiz.restservices;

import com.brielage.coursequiz.domain.DocentVak;
import com.brielage.coursequiz.domain.Rol;
import com.brielage.coursequiz.domain.StudentVak;
import com.brielage.coursequiz.domain.User;
import com.brielage.coursequiz.domain.Vak;
import com.brielage.coursequiz.jsonintermediates.JsonVak;
import com.brielage.coursequiz.services.DocentVakService;
import com.brielage.coursequiz.services.OpleidingService;
import com.brielage.coursequiz.services.StudentVakService;
import com.brielage.coursequiz.services.UserRolService;
import com.brielage.coursequiz.services.UserService;
import com.brielage.coursequiz.services.VakService;
import com.brielage.coursequiz.singleton.APIResponse;
import com.brielage.coursequiz.singleton.ResponseLogger;
import com.brielage.coursequiz.singleton.Tools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"FieldCanBeLocal", "unused", "OptionalGetWithoutIsPresent", "rawtypes", "unchecked"})
public class VakRestService {
    private final UserService userService;
    private final UserRolService userRolService;
    private final StudentVakService studentVakService;
    private final DocentVakService docentVakService;
    private final VakService vakService;
    private final OpleidingService opleidingService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public VakRestService(UserService userService,
                          UserRolService userRolService,
                          StudentVakService studentVakService,
                          DocentVakService docentVakService,
                          VakService vakService,
                          OpleidingService opleidingService) {
        this.userService = userService;
        this.userRolService = userRolService;
        this.studentVakService = studentVakService;
        this.docentVakService = docentVakService;
        this.vakService = vakService;
        this.opleidingService = opleidingService;
    }

    public String listVakken(JsonNode jsonNode)
            throws JsonProcessingException {
        // LOG
        ResponseLogger.logRequest("vak.list", jsonNode.toPrettyString());

        try {
            JsonVak jsonVak = objectMapper.treeToValue(jsonNode, JsonVak.class);
            String userkey = jsonVak.getUserkey();

            if (!Tools.userExists(userkey, userService))
                return APIResponse.respond(false, "andere");

            User u = userService.findByUserkey(userkey).get(0);
            Rol eigenrol = userRolService.findByUserId(u.getId()).get().getRol();
            List<Vak> vakList = new ArrayList<>();
            long userid = u.getId();

            if (eigenrol == Rol.STUDENT) {
                List<StudentVak> studentVakList = studentVakService.findByStudentId(userid);

                for (StudentVak sv : studentVakList)
                    vakList.add(vakService.findById(sv.getVakId()).get());
            }

            if (eigenrol == Rol.DOCENT) {
                List<DocentVak> docentVakList = docentVakService.findByDocentId(userid);

                for (DocentVak dv : docentVakList)
                    vakList.add(vakService.findById(dv.getVakId()).get());
            }

            List vakkenlijst = new ArrayList();

            for (Vak v : vakList) {
                Map m = new LinkedHashMap();
                m.put("id", v.getId());
                m.put("naam", v.getNaam());
                vakkenlijst.add(m);
            }

            return APIResponse.respondVak(true,
                    eigenrol,
                    vakkenlijst);
        } catch (UnrecognizedPropertyException | InvalidFormatException e) {
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

    public String listAllVakken(JsonNode jsonNode)
            throws JsonProcessingException {
        // LOG
        ResponseLogger.logRequest("vak.listAll", jsonNode.toPrettyString());

        try {
            JsonVak jsonVak = objectMapper.treeToValue(jsonNode, JsonVak.class);
            String userkey = jsonVak.getUserkey();

            if (!Tools.userExists(userkey, userService))
                return APIResponse.respond(false, "andere");

            User u = userService.findByUserkey(userkey).get(0);

            if (!Tools.isAdminOrDocent(u, userRolService))
                return APIResponse.respond(false, "rechten_ongeldig");

            return APIResponse.respondVak(true,
                    userRolService.findByUserId(u.getId()).get().getRol(),
                    vakService.findAll());
        } catch (UnrecognizedPropertyException | InvalidFormatException e) {
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
