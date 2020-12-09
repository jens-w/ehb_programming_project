package com.brielage.coursequiz.controllers;

import com.brielage.coursequiz.restservices.UserRestService;
import com.brielage.coursequiz.services.DocentService;
import com.brielage.coursequiz.services.DocentVakService;
import com.brielage.coursequiz.services.OpleidingService;
import com.brielage.coursequiz.services.StudentService;
import com.brielage.coursequiz.services.StudentVakService;
import com.brielage.coursequiz.services.UserRolService;
import com.brielage.coursequiz.services.UserService;
import com.brielage.coursequiz.services.VakService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SuppressWarnings({"unused", "FieldCanBeLocal"})
@RestController
@RequestMapping(value = "/user")
public class UserRestController {
    private final UserService userService;
    private final UserRolService userRolService;
    private final StudentService studentService;
    private final StudentVakService studentVakService;
    private final VakService vakService;
    private final DocentService docentService;
    private final DocentVakService docentVakService;
    private final OpleidingService opleidingService;

    private final UserRestService userRestService;

    public UserRestController(UserService userService,
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
        this.userRestService = new UserRestService(
                userService,
                userRolService,
                studentService,
                studentVakService,
                vakService,
                docentService,
                docentVakService,
                opleidingService);
    }

    @SuppressWarnings("rawtypes")
    @PostMapping(value = "/create",
            consumes = "application/json",
            produces = "application/json")
    public Map createUser(@RequestBody JsonNode jsonNode) {
        return userRestService.createUser(jsonNode);
    }

    @SuppressWarnings("rawtypes")
    @PostMapping(value = "/login",
            consumes = "application/json",
            produces = "application/json")
    public Map login(@RequestBody JsonNode jsonNode) {
        return userRestService.login(jsonNode);
    }
}
