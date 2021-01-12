package com.brielage.coursequiz.restservices;

import com.brielage.coursequiz.domain.Docent;
import com.brielage.coursequiz.domain.DocentVak;
import com.brielage.coursequiz.domain.Opleiding;
import com.brielage.coursequiz.domain.Rol;
import com.brielage.coursequiz.domain.Student;
import com.brielage.coursequiz.domain.StudentVak;
import com.brielage.coursequiz.domain.User;
import com.brielage.coursequiz.domain.UserRol;
import com.brielage.coursequiz.domain.Vak;
import com.brielage.coursequiz.jsonintermediates.JsonUser;
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
import com.brielage.coursequiz.singleton.APIResponse;
import com.brielage.coursequiz.singleton.ResponseLogger;
import com.brielage.coursequiz.singleton.Tools;

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

@SuppressWarnings({"unchecked", "DuplicatedCode", "OptionalGetWithoutIsPresent", "rawtypes"})
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


    public String createUser(JsonNode jsonNode)
            throws JsonProcessingException {
        // LOG
        ResponseLogger.logRequest("user.create", jsonNode.toPrettyString());

        try {
            JsonUser jsonUser = objectMapper.treeToValue(jsonNode, JsonUser.class);
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

                        userRolService.create(new UserRol(u.getId(), Rol.USER));

                        return APIResponse.respond(true);
                    }

                    return APIResponse.respond(false, "email_bestaatl_al");
                } catch (Exception e) {
                    e.printStackTrace();

                    // LOG
                    logger.error("\n" + e.getMessage());

                    return APIResponse.respond(false, "andere");
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

    public String login(JsonNode jsonNode)
            throws JsonProcessingException {
        // LOG
        ResponseLogger.logRequest("user.login", jsonNode.toPrettyString());

        try {
            JsonUser jsonUser = objectMapper.treeToValue(jsonNode, JsonUser.class);
            boolean emailGeldig = jsonUser.checkEmail();
            boolean passwordGeldig = jsonUser.checkPassword();

            if (!emailGeldig || !passwordGeldig) return APIResponse.respond(false);

            String email = jsonUser.getEmail();

            if (!Tools.doesUserEmailExist(email, userService))
                return APIResponse.respond(false);

            User user = userService.findByEmail(email).get(0);
            String password = jsonUser.getPassword();

            if (!password.equals(user.getPassword())) return APIResponse.respond(false);

            JsonUserResponse jsonUserResponse = new JsonUserResponse(
                    true,
                    user.getUserkey(),
                    user.getVoornaam(),
                    user.getFamilienaam(),
                    email,
                    user.getAvatarPad()
            );
            Optional<UserRol> optionalUserRol = userRolService.findByUserId(user.getId());

            if (optionalUserRol.isEmpty()) {
                userRolService.create(new UserRol(user.getId(), Rol.USER));
                jsonUserResponse.setRol(Rol.USER);
            } else {
                jsonUserResponse.setRol(optionalUserRol.get().getRol());
            }

            List<Student> studentListByUserId = studentService.findByUserId(user.getId());
            boolean isStudent = false;

            if (studentListByUserId.size() == 1) {
                isStudent = true;
                Student student = studentListByUserId.get(0);
                Optional<Opleiding> optionalOpleiding = opleidingService.findById(student.getOpleidingid());

                if (optionalOpleiding.isPresent()) {
                    Opleiding opleiding = optionalOpleiding.get();
                    Map opleidingMap = new LinkedHashMap();

                    opleidingMap.put("id", opleiding.getId());
                    opleidingMap.put("naam", opleiding.getNaam());

                    jsonUserResponse.setOpleiding(opleidingMap);
                }
            }

            List userVakkenList = null;

            if (isStudent)
                userVakkenList = studentVakService.findByStudentId(user.getId());
            else if (Tools.isDocent(user, userRolService))
                userVakkenList = docentVakService.findByDocentId(user.getId());

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

            return APIResponse.respond(false, fouten);
        } catch (JsonProcessingException e) {
            e.printStackTrace();

            // LOG
            logger.error("\n" + e.getMessage());

            return APIResponse.respond(false, "andere");
        }
    }

    public String edit(JsonNode jsonNode)
            throws JsonProcessingException {
        // LOG
        ResponseLogger.logRequest("user.edit", jsonNode.toPrettyString());

        try {
            JsonUser jsonUser = objectMapper.treeToValue(jsonNode, JsonUser.class);

            if (jsonUser.getVoornaam() != null && !jsonUser.checkVoornaam())
                return APIResponse.respond(false, "voornaam_ongeldig");

            if (jsonUser.getFamilienaam() != null && !jsonUser.checkFamielienaam())
                return APIResponse.respond(false, "familienaam_ongeldig");

            if (jsonUser.getEmail() != null && !jsonUser.checkEmail())
                return APIResponse.respond(false, "email_ongeldig");

            if (jsonUser.getAvatarpad() != null && !jsonUser.checkAvatarpad())
                return APIResponse.respond(false, "avatarpad_ongeldig");

            if (jsonUser.getPassword() != null && !jsonUser.checkPassword())
                return APIResponse.respond(false, "password_ongeldig");

            if (jsonUser.getEmail() != null && !Tools.doesUserEmailExist(jsonUser.getEmail(), userService))
                return APIResponse.respond(false, "email_bestaat_al");

            String userkey = jsonUser.getUserkey();

            if (!Tools.userExists(userkey, userService)) {
                // LOG
                logger.info("\nUSERKEY BESTAAT NIET OF MEERDERE KEREN");

                return APIResponse.respond(false, "andere");
            }

            User user = userService.findByUserkey(userkey).get(0);

            if (jsonUser.getVoornaam() != null) user.setVoornaam(jsonUser.getVoornaam());
            if (jsonUser.getFamilienaam() != null) user.setFamilienaam(jsonUser.getFamilienaam());
            if (jsonUser.getEmail() != null) user.setEmail(jsonUser.getEmail());
            if (jsonUser.getAvatarpad() != null) user.setAvatarPad(jsonUser.getAvatarpad());
            if (jsonUser.getPassword() != null) user.setPassword(jsonUser.getPassword());

            // needed to update the db?
            // maybe there's another way, but without this it would not update the db
            user = userService.findById(user.getId()).get();

            Map userData = new LinkedHashMap();

            userData.put("voornaam", user.getVoornaam());
            userData.put("familienaam", user.getFamilienaam());
            userData.put("email", user.getEmail());
            userData.put("eigenrol", userRolService.findByUserId(user.getId()).get().getRol());

            return APIResponse.respondUser(
                    true,
                    userData,
                    userRolService.findByUserId(user.getId()).get().getRol());
        } catch (UnrecognizedPropertyException e) {
            e.printStackTrace();

            Map fouten = new LinkedHashMap();
            fouten.put("veld_ongeldig", e.getPropertyName());

            // LOG
            logger.error("\n" + e.getMessage());

            return APIResponse.respond(false, fouten);
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

    public String newUserkey(JsonNode jsonNode)
            throws JsonProcessingException {
        // LOG
        ResponseLogger.logRequest("user.newuserkey", jsonNode.toPrettyString());

        try {
            JsonUser jsonUser = objectMapper.treeToValue(jsonNode, JsonUser.class);

            if (!jsonUser.getUserkey().isEmpty()) {
                List<User> userListByUserkey = userService.findByUserkey(jsonUser.getUserkey());

                if (userListByUserkey.isEmpty()) return APIResponse.respond(false);

                User u = userListByUserkey.get(0);
                u.setUserkey(generateUserkey());

                JsonUserResponse jsonUserResponse = new JsonUserResponse(
                        true,
                        u.getUserkey(),
                        null,
                        null,
                        null,
                        null,
                        userRolService.findByUserId(u.getId()).get().getRol());

                // LOG
                ResponseLogger.logJsonResponse(jsonUserResponse);

                return objectMapper.writeValueAsString(jsonUserResponse);
            }

            return APIResponse.respond(false);
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

    public String list(JsonNode jsonNode)
            throws JsonProcessingException {
        // LOG
        ResponseLogger.logRequest("user.list", jsonNode.toPrettyString());

        try {
            JsonUser jsonUser = objectMapper.treeToValue(jsonNode, JsonUser.class);
            String userkey = jsonUser.getUserkey();

            if (!Tools.userExists(userkey, userService)) {
                // LOG
                logger.info("\nERR::userkey does not exist");

                return APIResponse.respond(false, "andere");
            }

            User u = userService.findByUserkey(userkey).get(0);

            if (!Tools.isAdminOrDocent(u, userRolService))
                return APIResponse.respond(false, "rechten_ongeldig");

            String r = jsonUser.getRol();

            if (r == null || r.isEmpty() || r.isBlank())
                return APIResponse.respond(false, "rol_leeg");

            Rol eigenrol = userRolService.findByUserId(u.getId()).get().getRol();

            if (!r.equals("ALL")) {
                Rol rol = Rol.valueOf(jsonUser.getRol());
                boolean isDocent = Tools.isDocent(u, userRolService);

                if ((isDocent && rol == Rol.DOCENT) ||
                        (isDocent && rol == Rol.ADMIN))
                    return APIResponse.respond(false, "rechten_ongeldig");

                boolean isAdmin = Tools.isAdmin(u, userRolService);

                if (!isAdmin && rol == Rol.ADMIN)
                    return APIResponse.respond(false, "rechten_ongeldig");

                if (isAdmin && rol == Rol.ADMIN)
                    return APIResponse.respondUser(true, findAdmins(), eigenrol, Rol.ADMIN);

                if (rol == Rol.STUDENT)
                    return APIResponse.respondUser(true, findStudenten(), eigenrol, Rol.STUDENT);

                if (rol == Rol.DOCENT)
                    return APIResponse.respondUser(true, findDocenten(), eigenrol, Rol.DOCENT);

                if (rol == Rol.USER)
                    return APIResponse.respondUser(true, findRegularUsers(), eigenrol, Rol.USER);
            }

            List docenten = findDocenten();
            List studenten = findStudenten();
            List users = findRegularUsers();

            if (eigenrol.equals(Rol.ADMIN))
                return APIResponse.respondUser(true, findAdmins(), docenten, studenten, users, eigenrol);

            return APIResponse.respondUser(true, docenten, studenten, users, eigenrol);
        } catch (UnrecognizedPropertyException e) {
            e.printStackTrace();

            Map fouten = new LinkedHashMap();
            fouten.put("veld_ongeldig", e.getPropertyName());

            // LOG
            logger.error("\n" + e.getMessage());

            return APIResponse.respond(false, fouten);
        } catch (JsonProcessingException e) {
            e.printStackTrace();

            // LOG
            logger.error("\n" + e.getMessage());

            return APIResponse.respond(false, "andere");
        }
    }

    public String changeRol(JsonNode jsonNode)
            throws JsonProcessingException {
        // LOG
        ResponseLogger.logRequest("user.changeRol", jsonNode.toPrettyString());

        try {
            JsonUser jsonUser = objectMapper.treeToValue(jsonNode, JsonUser.class);

            if (!Tools.userExists(jsonUser.getUserkey(), userService)) {
                // LOG
                logger.info("\nERR::userkey does not exist");

                return APIResponse.respond(false, "andere");
            }

            long userid = jsonUser.getId();

            if (!Tools.userExists(userid, userService))
                return APIResponse.respond(false, "user_bestaat_niet");

            Rol nieuwerol = null;
            boolean isRolGeldig = false;
            String nieuweroltekst = jsonUser.getNieuwerol();

            for (Rol rol : Rol.values()) {
                if (nieuweroltekst.equals(rol.name())) {
                    isRolGeldig = true;
                    nieuwerol = rol;
                    break;
                }
            }

            if (!isRolGeldig)
                return APIResponse.respond(false, "rol_bestaat_niet");

            User u = userService.findById(userid).get();
            Rol rol = userRolService.findByUserId(userid).get().getRol();

            if (nieuwerol == Rol.STUDENT) {
                long opleidingid = jsonUser.getOpleidingid();

                if (opleidingid < 1)
                    return APIResponse.respond(false, "opleidingid_ongeldig");

                if (!Tools.doesOpleidingExist(opleidingid, opleidingService))
                    return APIResponse.respond(false, "opleidingid_bestaat_niet");

                if (rol == Rol.ADMIN || rol == Rol.DOCENT)
                    return APIResponse.respond(false, "user_heeft_meer_rechten");

                if (nieuwerol == rol)
                    return APIResponse.respond(false, "user_heeft_zelfde_rechten");

                studentService.add(userid, opleidingid);
                UserRol userRol = userRolService.findByUserId(userid).get();
                userRol.setRol(Rol.STUDENT);
            }

            if (nieuwerol == Rol.DOCENT) {
                long vakid = jsonUser.getVakid();

                if (vakid < 1)
                    return APIResponse.respond(false, "vakid_ongeldig");

                if (!Tools.doesVakExist(vakid, vakService))
                    return APIResponse.respond(false, "vakid_bestaat_niet");

                if (rol == Rol.ADMIN)
                    return APIResponse.respond(false, "user_heeft_meer_rechten");

                if (rol == nieuwerol)
                    if (Tools.isDocentVanVak(u, vakid, docentVakService))
                        return APIResponse.respond(false, "user_heeft_zelfde_rechten");

                if (rol == Rol.STUDENT)
                    return APIResponse.respond(false, "user_is_student");

                docentService.add(userid);
                UserRol userRol = userRolService.findByUserId(userid).get();
                userRol.setRol(Rol.DOCENT);
                docentVakService.create(new DocentVak(userid, vakid));
            }

            Rol eigenrol = userRolService.findByUserId(
                    userService.findByUserkey(
                            jsonUser.getUserkey()).get(0).getId())
                    .get().getRol();

            return APIResponse.respond(true, eigenrol);
        } catch (UnrecognizedPropertyException e) {
            e.printStackTrace();

            Map fouten = new LinkedHashMap();
            fouten.put("veld_ongeldig", e.getPropertyName());

            // LOG
            logger.error("\n" + e.getMessage());

            return APIResponse.respond(false, fouten);
        } catch (JsonProcessingException e) {
            e.printStackTrace();

            // LOG
            logger.error("\n" + e.getMessage());

            return APIResponse.respond(false, "andere");
        }
    }

    public String vakAdd(JsonNode jsonNode)
            throws JsonProcessingException {
        // LOG
        ResponseLogger.logRequest("user.vakAdd", jsonNode.toPrettyString());

        try {
            JsonUser jsonUser = objectMapper.treeToValue(jsonNode, JsonUser.class);

            if (!Tools.userExists(jsonUser.getUserkey(), userService)) {
                // LOG
                logger.info("\nERR::userkey does not exist");

                return APIResponse.respond(false, "andere");
            }

            long userid = jsonUser.getId();

            if (!Tools.userExists(userid, userService))
                return APIResponse.respond(false, "user_bestaat_niet");

            Rol rol = userRolService.findByUserId(userid).get().getRol();

            boolean isStudent = rol == Rol.STUDENT;
            boolean isDocent = rol == Rol.DOCENT;

            if (!(isStudent || isDocent))
                return APIResponse.respond(false, "ongeldige_rol");

            long vakid = jsonUser.getVakid();

            if (vakid < 1)
                return APIResponse.respond(false, "vakid_ongeldig");

            if (!Tools.doesVakExist(vakid, vakService))
                return APIResponse.respond(false, "vakid_bestaat_niet");

            List<Vak> vakkenlijst = new ArrayList<>();

            if (isStudent) {
                List<StudentVak> studentVakList = studentVakService.findByStudentId(userid);
                for (StudentVak sv : studentVakList)
                    vakkenlijst.add(vakService.findById(sv.getVakId()).get());
            }

            if (isDocent) {
                List<DocentVak> docentVakList = docentVakService.findByDocentId(userid);
                for (DocentVak dv : docentVakList)
                    vakkenlijst.add(vakService.findById(dv.getVakId()).get());
            }

            Vak vak = vakService.findById(vakid).get();

            if (vakkenlijst.contains(vak))
                return APIResponse.respond(false, "user_heeft_vak_al");

            if (isStudent)
                studentVakService.create(new StudentVak(userid, vakid));

            if (isDocent)
                docentVakService.create(new DocentVak(userid, vakid));

            Rol eigenrol =
                    userRolService.findByUserId(userService.findByUserkey(
                            jsonUser.getUserkey()).get(0).getId()
                    ).get().getRol();

            return APIResponse.respond(true, eigenrol);
        } catch (UnrecognizedPropertyException e) {
            e.printStackTrace();

            Map fouten = new LinkedHashMap();
            fouten.put("veld_ongeldig", e.getPropertyName());

            // LOG
            logger.error("\n" + e.getMessage());

            return APIResponse.respond(false, fouten);
        } catch (JsonProcessingException e) {
            e.printStackTrace();

            // LOG
            logger.error("\n" + e.getMessage());

            return APIResponse.respond(false, "andere");
        }
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

    public List findStudenten() {
        List<Student> sl = studentService.findAll();
        List studenten = new ArrayList();

        for (Student s : sl) {
            Map student = new LinkedHashMap();

            student.put("id", s.getId());
            student.put("voornaam", s.getVoornaam());
            student.put("familienaam", s.getFamilienaam());
            student.put("email", s.getEmail());

            studenten.add(student);
        }

        return studenten;
    }

    public List findDocenten() {
        List<Docent> dl = docentService.findAll();
        List docenten = new ArrayList();

        for (Docent d : dl) {
            Map docent = new LinkedHashMap();

            docent.put("id", d.getId());
            docent.put("voornaam", d.getVoornaam());
            docent.put("familienaam", d.getFamilienaam());
            docent.put("email", d.getEmail());

            docenten.add(docent);
        }

        return docenten;
    }

    public List findRegularUsers() {
        List<User> ul = userService.findAllRegularUsers();
        List users = new ArrayList();

        for (User us : ul) {
            Map user = new LinkedHashMap();

            user.put("id", us.getId());
            user.put("voornaam", us.getVoornaam());
            user.put("familienaam", us.getFamilienaam());
            user.put("email", us.getEmail());

            users.add(user);
        }

        return users;
    }

    public List findAdmins() {
        List<User> allAdmins = userService.findAllAdmins();
        List admins = new ArrayList<>();

        for (User ua : allAdmins) {
            Map admin = new LinkedHashMap();

            admin.put("id", ua.getId());
            admin.put("voornaam", ua.getVoornaam());
            admin.put("familienaam", ua.getFamilienaam());
            admin.put("email", ua.getEmail());

            admins.add(admin);
        }

        return admins;
    }
}
