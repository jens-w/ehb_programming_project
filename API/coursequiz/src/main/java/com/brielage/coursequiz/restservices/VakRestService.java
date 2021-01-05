package com.brielage.coursequiz.restservices;

import com.brielage.coursequiz.domain.DocentVak;
import com.brielage.coursequiz.domain.JsonVak;
import com.brielage.coursequiz.domain.Rol;
import com.brielage.coursequiz.domain.StudentVak;
import com.brielage.coursequiz.domain.User;
import com.brielage.coursequiz.domain.UserRol;
import com.brielage.coursequiz.domain.Vak;
import com.brielage.coursequiz.services.DocentVakService;
import com.brielage.coursequiz.services.OpleidingService;
import com.brielage.coursequiz.services.StudentVakService;
import com.brielage.coursequiz.services.UserRolService;
import com.brielage.coursequiz.services.UserService;
import com.brielage.coursequiz.services.VakService;
import com.brielage.coursequiz.singleton.APIResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    public String listVakken(JsonNode jsonNode) {
        // LOG
        logRequest(jsonNode.toPrettyString());

        try {
            JsonVak jsonVak = objectMapper.treeToValue(jsonNode, JsonVak.class);

            // LOG
            logger.info("\njsonVak: " + jsonVak.toString());

            //if (!Tools.doesUserHaveRights(jsonVak.getUserkey(), userService, userRolService))
            //    return APIResponse.respond(false, "rechten_ongeldig");

            List<User> userListByUserkey = userService.findByUserkey(jsonVak.getUserkey());
            User user = userListByUserkey.get(0);
            UserRol userRol = userRolService.findByUserId(user.getId()).get();
            List<Vak> vakList = new ArrayList<>();

            if (userRol.getRol() == Rol.STUDENT) {
                List<StudentVak> studentVakList = studentVakService.findByStudentId(user.getId());

                for (StudentVak sv : studentVakList) {
                    vakList.add(vakService.findById(sv.getVakId()).get());
                }
            }

            if (userRol.getRol() == Rol.DOCENT) {
                List<DocentVak> docentVakList = docentVakService.findByDocentId(user.getId());

                for (DocentVak dv : docentVakList) {
                    vakList.add(vakService.findById(dv.getVakId()).get());
                }
            }

            List vakkenlijst = new ArrayList();

            for (Vak v : vakList) {
                Map m = new LinkedHashMap();
                m.put("id", v.getId());
                m.put("naam", v.getNaam());
                vakkenlijst.add(m);
            }

            return APIResponse.respondVak(true,
                    userRol.getRol(),
                    vakkenlijst);


        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void logRequest(String s) {
        logger.info("\nrequest:\n" + s);
    }
}
