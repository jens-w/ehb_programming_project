package com.brielage.coursequiz.restservices;

import com.brielage.coursequiz.singleton.APIResponse;
import com.brielage.coursequiz.domain.Docent;
import com.brielage.coursequiz.domain.DocentVak;
import com.brielage.coursequiz.jsonintermediates.JsonUser;
import com.brielage.coursequiz.domain.Opleiding;
import com.brielage.coursequiz.singleton.ResponseLogger;
import com.brielage.coursequiz.domain.Rol;
import com.brielage.coursequiz.domain.Student;
import com.brielage.coursequiz.domain.StudentVak;
import com.brielage.coursequiz.domain.User;
import com.brielage.coursequiz.domain.UserRol;
import com.brielage.coursequiz.domain.Vak;
import com.brielage.coursequiz.restresponses.JsonResponse;
import com.brielage.coursequiz.restresponses.JsonUserResponse;
import com.brielage.coursequiz.services.DocentService;
import com.brielage.coursequiz.services.DocentVakService;
import com.brielage.coursequiz.services.OpleidingService;
import com.brielage.coursequiz.services.StudentService;
import com.brielage.coursequiz.services.StudentVakService;
import com.brielage.coursequiz.services.UserRolService;
import com.brielage.coursequiz.services.UserService;
import com.brielage.coursequiz.services.VakService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@SuppressWarnings({"unchecked", "DuplicatedCode"})
public class UserRestService {
    private final UserService userService;
    private final UserRolService userRolService;
    private final StudentService studentService;
    private final StudentVakService studentVakService;
    private final VakService vakService;
    private final DocentService docentService;
    private final DocentVakService docentVakService;
    private final OpleidingService opleidingService;
    private final ObjectMapper objectMapper = new ObjectMapper();

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
    public String createUser(JsonNode jsonNode) throws JsonProcessingException {
        // LOG
        logRequest(jsonNode.toPrettyString());

        try {
            JsonUser jsonUser = objectMapper.treeToValue(jsonNode, JsonUser.class);

            // LOG
            logger.info("\njsonUser:\n" + jsonUser.toString());

            boolean voornaamGeldig = jsonUser.checkVoornaam();
            boolean familienaamGeldig = jsonUser.checkFamielienaam();
            boolean emailGeldig = jsonUser.checkEmail();
            boolean passwordGeldig = jsonUser.checkPassword();

            if (!voornaamGeldig ||
                    !familienaamGeldig ||
                    !emailGeldig ||
                    !passwordGeldig) {
                List fouten = new ArrayList();

                if (!voornaamGeldig) fouten.add("voornaam_ongeldig");
                if (!familienaamGeldig) fouten.add("familienaam_ongeldig");
                if (!emailGeldig) fouten.add("email_ongeldig");
                if (!passwordGeldig) fouten.add("password_ongeldig");

                return APIResponse.respond(false, fouten);
            } else {
                String defaultAvatarPad = "\\public\\images\\account\\accountinfo\\avatar_default.png";
                User u = new User(
                        jsonUser.getVoornaam(),
                        jsonUser.getFamilienaam(),
                        jsonUser.getEmail(),
                        defaultAvatarPad,
                        jsonUser.getPassword()
                );

                try {
                    List<User> userListByEmail = userService.findByEmail(u.getEmail());

                    if (userListByEmail.isEmpty()) {
                        u.setUserkey(generateUserkey());
                        userService.create(u);

                        UserRol ur = new UserRol(u.getId(), Rol.USER);
                        userRolService.create(ur);

                        return APIResponse.respond(true);
                    }

                    return APIResponse.respond(false,"email_bestaatl_al");
                } catch (Exception e) {
                    e.printStackTrace();

                    // LOG
                    logger.error("\n" + e.getMessage());

                    return APIResponse.respond(false,"andere");
                }
            }
        } catch (UnrecognizedPropertyException e) {
            e.printStackTrace();

            // LOG
            logger.error("\n" + e.getMessage());



            Map fouten = new LinkedHashMap();
            fouten.put("veld_ongeldig", e.getPropertyName());


            ResponseLogger.logJsonResponse(new JsonResponse(false, fouten));

            return objectMapper.writeValueAsString(new JsonResponse(false, fouten));
        } catch (Exception e) {
            e.printStackTrace();

            Map fouten = new LinkedHashMap();
            fouten.put("andere", true);

            // LOG
            logger.info("\n" + e.getMessage());
            ResponseLogger.logJsonResponse(new JsonResponse(false, fouten));

            return objectMapper.writeValueAsString(new JsonResponse(false, fouten));
        }
    }

    @SuppressWarnings("rawtypes")
    public String login(JsonNode jsonNode) throws JsonProcessingException {
        // LOG
        logRequest(jsonNode.toPrettyString());

        try {
            JsonUser jsonUser = objectMapper.treeToValue(jsonNode, JsonUser.class);

            // LOG
            logger.info("\njsonUser" + jsonUser.toString());

            boolean emailGeldig = jsonUser.checkEmail();
            boolean passwordGeldig = jsonUser.checkPassword();

            if (!emailGeldig || !passwordGeldig) {
                // LOG
                ResponseLogger.logJsonResponse(new JsonResponse(false));

                return objectMapper.writeValueAsString(new JsonResponse(false));
            }

            List<User> userList = userService.findByEmail(jsonUser.getEmail());

            if (userList.size() != 1) {
                // LOG
                ResponseLogger.logJsonResponse(new JsonResponse(false));

                return objectMapper.writeValueAsString(new JsonResponse(false));
            }

            User user = userList.get(0);

            if (!jsonUser.getPassword().equals(user.getPassword())) {
                // LOG
                ResponseLogger.logJsonResponse(new JsonResponse(false));

                return objectMapper.writeValueAsString(new JsonResponse(false));
            }

            JsonUserResponse jsonUserResponse = new JsonUserResponse(
                    true,
                    user.getUserkey(),
                    user.getVoornaam(),
                    user.getFamilienaam(),
                    user.getEmail(),
                    user.getAvatarPad()
            );
            Optional<UserRol> optionalUserRol = userRolService.findByUserId(user.getId());

            if (optionalUserRol.isEmpty()) {
                userRolService.create(new UserRol(user.getId(), Rol.USER));
                jsonUserResponse.setRol(Rol.USER);
            } else {
                jsonUserResponse.setRol(optionalUserRol.get().getRol());
            }

            Optional<Student> optionalStudent = studentService.findById(user.getId());
            boolean isStudent = false;

            if (optionalStudent.isPresent()) {
                isStudent = true;
                Student student = optionalStudent.get();
                Optional<Opleiding> optionalOpleiding = opleidingService.findById(student.getOpleidingid());

                if (optionalOpleiding.isPresent()) {
                    Opleiding opleiding = optionalOpleiding.get();
                    Map opleidingMap = new LinkedHashMap();

                    opleidingMap.put("id", opleiding.getId());
                    opleidingMap.put("naam", opleiding.getNaam());

                    jsonUserResponse.setOpleiding(opleidingMap);
                }
            }

            Optional<Docent> optionalDocent = docentService.findById(user.getId());
            boolean isDocent = optionalDocent.isPresent();

            List userVakkenList = null;

            if (isStudent) {
                userVakkenList = studentVakService.findByStudentId(user.getId());
            } else if (isDocent) {
                userVakkenList = docentVakService.findByDocentId(user.getId());
            }

            if (userVakkenList != null && !userVakkenList.isEmpty()) {
                List vakkenList = new ArrayList();

                for (Object o : userVakkenList) {
                    Optional<Vak> optionalVak;

                    if (isStudent) {
                        StudentVak sv = (StudentVak) o;
                        optionalVak = vakService.findById(sv.getVakId());
                    } else {
                        DocentVak dv = (DocentVak) o;
                        optionalVak = vakService.findById(dv.getVakId());
                    }

                    if (optionalVak.isPresent()) {
                        Vak v = optionalVak.get();
                        Map map = new LinkedHashMap();

                        map.put("id", v.getId());
                        map.put("naam", v.getNaam());

                        vakkenList.add(map);
                    }
                }

                if (!vakkenList.isEmpty()) jsonUserResponse.setVakken(vakkenList);
            }

            // LOG
            logger.info("\n" + jsonUser.toString());
            ResponseLogger.logJsonResponse(jsonUserResponse);

            return objectMapper.writeValueAsString(jsonUserResponse);
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

            // LOG
            logger.error("\n" + e.getMessage());
            ResponseLogger.logJsonResponse(new JsonResponse(false));

            return objectMapper.writeValueAsString(new JsonResponse(false));
        }
    }

    @SuppressWarnings("rawtypes")
    public String edit(JsonNode jsonNode) throws JsonProcessingException {
        // LOG
        logRequest(jsonNode.toPrettyString());

        try {
            JsonUser jsonUser = objectMapper.treeToValue(jsonNode, JsonUser.class);

            // LOG
            logger.info("\njsonUser" + jsonUser.toString());

            if (jsonUser.getVoornaam() != null && !jsonUser.checkVoornaam()) {
                Map fouten = new LinkedHashMap();
                fouten.put("voornaam_ongeldig", true);

                // LOG
                ResponseLogger.logJsonResponse(new JsonResponse(false, fouten));

                return objectMapper.writeValueAsString(new JsonResponse(false, fouten));
            }

            if (jsonUser.getFamilienaam() != null && !jsonUser.checkFamielienaam()) {
                Map fouten = new LinkedHashMap();
                fouten.put("familienaam_ongeldig", true);

                // LOG
                ResponseLogger.logJsonResponse(new JsonResponse(false, fouten));

                return objectMapper.writeValueAsString(new JsonResponse(false, fouten));
            }

            if (jsonUser.getEmail() != null && !jsonUser.checkEmail()) {
                Map fouten = new LinkedHashMap();
                fouten.put("email_ongeldig", true);

                // LOG
                ResponseLogger.logJsonResponse(new JsonResponse(false, fouten));

                return objectMapper.writeValueAsString(new JsonResponse(false, fouten));
            }

            if (jsonUser.getAvatarpad() != null && !jsonUser.checkAvatarpad()) {
                Map fouten = new LinkedHashMap();
                fouten.put("avatarpad_ongeldig", true);

                // LOG
                ResponseLogger.logJsonResponse(new JsonResponse(false, fouten));

                return objectMapper.writeValueAsString(new JsonResponse(false, fouten));
            }

            if (jsonUser.getPassword() != null && !jsonUser.checkPassword()) {
                Map fouten = new LinkedHashMap();
                fouten.put("password_ongeldig", true);

                // LOG
                ResponseLogger.logJsonResponse(new JsonResponse(false, fouten));

                return objectMapper.writeValueAsString(new JsonResponse(false, fouten));
            }

            List<User> userListByEmail = userService.findByEmail(jsonUser.getEmail());

            if (userListByEmail.size() != 0) {
                Map fouten = new LinkedHashMap();
                fouten.put("email_bestaat_al", true);

                // LOG
                ResponseLogger.logJsonResponse(new JsonResponse(false, fouten));

                return objectMapper.writeValueAsString(new JsonResponse(false, fouten));
            }

            List<User> userListByUserkey = userService.findByUserkey(jsonUser.getUserkey());

            if (userListByUserkey.size() != 1) {
                Map fouten = new LinkedHashMap();
                fouten.put("andere", true);

                // LOG
                logger.info("\nUSERKEY BESTAAT NIET OF MEERDERE KEREN");
                ResponseLogger.logJsonResponse(new JsonResponse(false, fouten));

                return objectMapper.writeValueAsString(new JsonResponse(false, fouten));
            }

            User user = userListByUserkey.get(0);

            if (jsonUser.getVoornaam() != null) user.setVoornaam(jsonUser.getVoornaam());
            if (jsonUser.getFamilienaam() != null) user.setFamilienaam(jsonUser.getFamilienaam());
            if (jsonUser.getEmail() != null) user.setEmail(jsonUser.getEmail());
            if (jsonUser.getAvatarpad() != null) user.setAvatarPad(jsonUser.getAvatarpad());
            if (jsonUser.getPassword() != null) user.setPassword(jsonUser.getPassword());

            // needed to update the db?
            // maybe there's another way, but without this it would not update the db
            //noinspection UnusedAssignment,OptionalGetWithoutIsPresent
            user = userService.findById(user.getId()).get();

            // LOG
            ResponseLogger.logJsonResponse(new JsonResponse(true));

            return objectMapper.writeValueAsString(new JsonResponse(true));
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
            logger.error("\n" + e.getMessage());
            ResponseLogger.logJsonResponse(new JsonResponse(false, fouten));

            return objectMapper.writeValueAsString(new JsonResponse(false, fouten));
        }
    }

    @SuppressWarnings("rawtypes")
    public String newUserkey(JsonNode jsonNode) throws JsonProcessingException {
        // LOG
        logRequest(jsonNode.toPrettyString());

        try {
            JsonUser jsonUser = objectMapper.treeToValue(jsonNode, JsonUser.class);

            // LOG
            logger.info("\njsonUser" + jsonUser.toString());

            if (!jsonUser.getUserkey().isEmpty()) {
                List<User> userListByUserkey = userService.findByUserkey(jsonUser.getUserkey());

                if (userListByUserkey.isEmpty()) {
                    // LOG
                    ResponseLogger.logJsonResponse(new JsonResponse(false));

                    return objectMapper.writeValueAsString(new JsonResponse(false));
                }

                User u = userListByUserkey.get(0);
                u.setUserkey(generateUserkey());

                JsonUserResponse jsonUserResponse = new JsonUserResponse(
                        true, u.getUserkey(), null, null, null, null);
                //noinspection OptionalGetWithoutIsPresent
                jsonUserResponse.setRol(userRolService.findByUserId(u.getId()).get().getRol());

                // LOG
                ResponseLogger.logJsonResponse(jsonUserResponse);

                return objectMapper.writeValueAsString(jsonUserResponse);
            }

            // LOG
            ResponseLogger.logJsonResponse(new JsonResponse(false));

            return objectMapper.writeValueAsString(new JsonResponse(false));
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
            logger.error("\n" + e.getMessage());
            ResponseLogger.logJsonResponse(new JsonResponse(false, fouten));

            return objectMapper.writeValueAsString(new JsonResponse(false, fouten));
        }
    }

    public void logRequest(String s) {
        logger.info("\nrequest:\n" + s);
    }



    public String generateUserkey() {
        String chars = "abcdefghijklmnopqrstuvwxyz" +
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                "0123456789";
        int length = 20;
        char[] userkey = new char[length];
        boolean userkeyUnique = false;
        List<String> userkeys = userService.findUserkeys();

        while (!userkeyUnique) {
            int i = 0;
            Random random = new Random();

            while (i < length) {
                int index = (int) (random.nextFloat() * chars.length());
                userkey[i] = chars.charAt(index);
                i++;
            }

            if (!userkeys.contains(String.valueOf(userkey))) userkeyUnique = true;
        }

        return String.valueOf(userkey);
    }
}
