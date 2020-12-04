package com.brielage.coursequiz.controllers;

import com.brielage.coursequiz.restservices.UserRestService;
import com.brielage.coursequiz.services.StudentService;
import com.brielage.coursequiz.services.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SuppressWarnings("unused")
@RestController
@RequestMapping(value = "/user")
public class UserRestController {
    @SuppressWarnings("FieldCanBeLocal")
    private final StudentService studentService;
    @SuppressWarnings("FieldCanBeLocal")
    private final UserService userService;
    private final UserRestService userRestService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public UserRestController(StudentService studentService,
                              UserService userService) {
        this.studentService = studentService;
        this.userService = userService;
        this.userRestService = new UserRestService(userService);
    }

    @SuppressWarnings("rawtypes")
    @PostMapping(value = "/create",
            consumes = "application/json",
            produces = "application/json")
    public Map createUser(@RequestBody JsonNode jsonNode) {
        return userRestService.createUser(jsonNode);
    }
}
