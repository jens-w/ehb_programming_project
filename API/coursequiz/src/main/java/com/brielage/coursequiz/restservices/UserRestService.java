package com.brielage.coursequiz.restservices;

import com.brielage.coursequiz.domain.Docent;
import com.brielage.coursequiz.domain.DocentVak;
import com.brielage.coursequiz.domain.JsonResponse;
import com.brielage.coursequiz.domain.Opleiding;
import com.brielage.coursequiz.domain.Rol;
import com.brielage.coursequiz.domain.Student;
import com.brielage.coursequiz.domain.StudentVak;
import com.brielage.coursequiz.domain.User;
import com.brielage.coursequiz.domain.UserRol;
import com.brielage.coursequiz.domain.Vak;
import com.brielage.coursequiz.domain.Validator;
import com.brielage.coursequiz.services.DocentService;
import com.brielage.coursequiz.services.DocentVakService;
import com.brielage.coursequiz.services.OpleidingService;
import com.brielage.coursequiz.services.StudentService;
import com.brielage.coursequiz.services.StudentVakService;
import com.brielage.coursequiz.services.UserRolService;
import com.brielage.coursequiz.services.UserService;
import com.brielage.coursequiz.services.VakService;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings("unchecked")
public class UserRestService {
    private final UserService userService;
    private final UserRolService userRolService;
    private final StudentService studentService;
    private final StudentVakService studentVakService;
    private final VakService vakService;
    private final DocentService docentService;
    private final DocentVakService docentVakService;
    private final OpleidingService opleidingService;

    private final Validator validator = new Validator();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public UserRestService(UserService userService,
                           UserRolService userRolService,
                           StudentService studentService,
                           StudentVakService studentVakService,
                           VakService vakService,
                           DocentService docentService,
                           DocentVakService docentVakService,
                           OpleidingService opleidingService) {
        this.userService = userService;
        this.userRolService = userRolService;
        this.studentService = studentService;
        this.studentVakService = studentVakService;
        this.vakService = vakService;
        this.docentService = docentService;
        this.docentVakService = docentVakService;
        this.opleidingService = opleidingService;
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

        JsonResponse jsonResponse;
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
            List errorList = new ArrayList();
            LinkedHashMap fouten = new LinkedHashMap();
            jsonResponse = new JsonResponse(false);

            if (!voornaamGeldig) fouten.put("voornaam_ongeldig", true);
            if (!familienaamGeldig) fouten.put("familienaam_ongeldig", true);
            if (!emailGeldig) fouten.put("email_ongeldig", true);
            if (!passwordGeldig) fouten.put("password_ongeldig", true);

            errorList.add(fouten);
            jsonResponse.add("errors", errorList);
        } else {
            User u = new User(voornaam, familienaam, email, "", password);
            String defaultAvatarPad = "\\public\\images\\account\\accountinfo\\avatar_default.png";
            u.setAvatarPad(defaultAvatarPad);
            logger.info("\nuser info:");
            logger.info(u.toString());
            logger.info("\n");

            try {
                userService.create(u);
                UserRol ur = new UserRol(u.getId(), Rol.USER);
                userRolService.create(ur);
                jsonResponse = new JsonResponse(true);
                return jsonResponse.getContent();
            } catch (Exception e) {
                logger.error(e.getMessage());

                List errorList = new ArrayList();
                LinkedHashMap fouten = new LinkedHashMap();
                jsonResponse = new JsonResponse(false);

                fouten.put("andere", true);
                errorList.add(fouten);
                jsonResponse.add("errors", errorList);
            }

        }

        logger.info("\n");
        logger.info(jsonResponse.getContent().toString());

        return jsonResponse.getContent();
    }

    @SuppressWarnings("rawtypes")
    public Map login(JsonNode jsonNode) {
        /*
            REQUEST
            {
                "email" : "mijn@ema.il",
                "password" : "mijn_password12!34"
            }

            RESPONSE SUCCESS
            {
                "success" : true,
                "userkey" : "userkey1234",
                "voornaam" : "mijn_voornaam",
                "familienaam" : "mijn_familienaam",
                "email" : "mijn@ema.il",
                "avatarpad" : "/pad/naar/avatar",
                "opleiding" : {
                    "id" : 3,
                    "naam" : "opleiding_naam"
                },
                "vakken" : [
                    "vak1" : {
                        "vakid" : 8,
                        "vaknaam" : "naam van het vak"
                    },
                    "vak2" : {
                        "vakid" : 1,
                        "vaknaam" : "naam van het vak"
                    }
                ],
                "eigenrol" : "admin/docent/student"
            }

            RESPONSE FAIL
            {
                "success" : false
            }
         */

        JsonResponse jsonResponse;
        String email = "";
        String password = "";
        Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();

        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            String key = entry.getKey();
            String value = entry.getValue().asText();

            switch (key) {
                case "email" -> email = value;
                case "password" -> password = value;
            }
        }

        boolean emailGeldig = validator.validateEmail(email);
        boolean passwordGeldig = validator.validatePassword(password);

        if (!emailGeldig || !passwordGeldig)
            return new JsonResponse(false).getContent();

        List<User> userList = userService.findByEmail(email);

        if (userList.size() != 1) return new JsonResponse(false).getContent();

        User user = userList.get(0);

        if (!password.equals(user.getPassword())) return new JsonResponse(false).getContent();

        jsonResponse = new JsonResponse(true);
        LinkedHashMap message = new LinkedHashMap();

        jsonResponse.add("userkey", user.getUserkey());
        jsonResponse.add("voornaam", user.getVoornaam());
        jsonResponse.add("familienaam", user.getFamilienaam());
        jsonResponse.add("email", user.getEmail());
        jsonResponse.add("avatarpad", user.getAvatarPad());

        Optional<UserRol> optionalUserRol = userRolService.findByUserId(user.getId());

        if (optionalUserRol.isPresent()) {
            jsonResponse.add("eigenrol", optionalUserRol.get().getRol());
        } else {
            jsonResponse.add("eigenrol", "USER");
        }

        Optional<Student> optionalStudent = studentService.findById(user.getId());

        if (optionalStudent.isPresent()) {
            logger.info("student!");

            Student student = optionalStudent.get();
            Optional<Opleiding> optionalOpleiding = opleidingService.findById(student.getOpleidingid());

            if (optionalOpleiding.isPresent()) {
                Opleiding opleiding = optionalOpleiding.get();
                LinkedHashMap opleidingWaardes = new LinkedHashMap();

                opleidingWaardes.put("id", opleiding.getId());
                opleidingWaardes.put("naam", opleiding.getNaam());

                jsonResponse.add("opleiding", opleidingWaardes);
            }

            logger.info("\n" + student.getId() + "\n");

            List<StudentVak> studentVakList = studentVakService.findByStudentId(student.getId());

            if (!studentVakList.isEmpty()) {
                logger.info("\nstudentenvakken\n");
                LinkedHashMap vakkenMap = new LinkedHashMap();
                @SuppressWarnings("DuplicatedCode")
                int i = 1;

                //noinspection DuplicatedCode
                for (StudentVak sv : studentVakList) {
                    Optional<Vak> optionalVak = vakService.findById(sv.getVakId());

                    if (optionalVak.isPresent()) {
                        Vak v = optionalVak.get();
                        LinkedHashMap map = new LinkedHashMap();

                        map.put("id", v.getId());
                        map.put("naam", v.getNaam());

                        vakkenMap.put("vak" + i, map);
                        i++;
                    }
                }

                logger.info("");
                logger.info(vakkenMap.toString());
                logger.info("");
                if (!vakkenMap.isEmpty()) jsonResponse.add("vakken", vakkenMap);
            }
        }

        Optional<Docent> optionalDocent = docentService.findById(user.getId());

        if (optionalDocent.isPresent()) {
            logger.info("docent!");

            Docent docent = optionalDocent.get();
            List<DocentVak> docentVakList = docentVakService.findByDocentId(docent.getId());

            if (!docentVakList.isEmpty()) {
                LinkedHashMap vakkenMap = new LinkedHashMap();
                int i = 1;

                for (DocentVak dv : docentVakList) {
                    Optional<Vak> optionalVak = vakService.findById(dv.getVakId());

                    if (optionalVak.isPresent()) {
                        Vak v = optionalVak.get();
                        LinkedHashMap map = new LinkedHashMap();

                        map.put("id", v.getId());
                        map.put("naam", v.getNaam());

                        vakkenMap.put("vak" + i, map);
                        i++;
                    }
                }

                if (!vakkenMap.isEmpty()) jsonResponse.add("vakken", vakkenMap);
            }
        }

        return jsonResponse.getContent();
    }
}
