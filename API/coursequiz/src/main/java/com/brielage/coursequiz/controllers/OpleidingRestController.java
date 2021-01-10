package com.brielage.coursequiz.controllers;

import com.brielage.coursequiz.restservices.OpleidingRestService;
import com.brielage.coursequiz.services.OpleidingService;
import com.brielage.coursequiz.services.StudentService;
import com.brielage.coursequiz.services.UserRolService;
import com.brielage.coursequiz.services.UserService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
@RestController
@RequestMapping(value = "/opleiding")
public class OpleidingRestController {
    private final UserService userService;
    private final UserRolService userRolService;
    private final OpleidingService opleidingService;
    private final StudentService studentService;

    private final OpleidingRestService opleidingRestService;

    public OpleidingRestController(UserService userService,
                                   UserRolService userRolService,
                                   OpleidingService opleidingService,
                                   StudentService studentService) {
        this.userService = userService;
        this.userRolService = userRolService;
        this.opleidingService = opleidingService;
        this.studentService = studentService;
        this.opleidingRestService = new OpleidingRestService(
                userService,
                userRolService,
                opleidingService,
                studentService
        );
    }

    @PostMapping(value = "/create",
            consumes = "application/json",
            produces = "application/json")
    public String createOpleiding(@RequestBody JsonNode jsonNode)
            throws JsonProcessingException {
        return opleidingRestService.createOpleiding(jsonNode);
    }

    @PostMapping(value = "/remove",
            consumes = "application/json",
            produces = "application/json")
    public String removeOpleiding(@RequestBody JsonNode jsonNode)
            throws JsonProcessingException {
        return opleidingRestService.removeOpleiding(jsonNode);
    }
}
