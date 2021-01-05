package com.brielage.coursequiz.controllers;

import com.brielage.coursequiz.restservices.VakRestService;
import com.brielage.coursequiz.services.DocentVakService;
import com.brielage.coursequiz.services.OpleidingService;
import com.brielage.coursequiz.services.StudentVakService;
import com.brielage.coursequiz.services.UserRolService;
import com.brielage.coursequiz.services.UserService;
import com.brielage.coursequiz.services.VakService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/vak")
public class VakRestController {
    private final UserService userService;
    private final UserRolService userRolService;
    private final StudentVakService studentVakService;
    private final DocentVakService docentVakService;
    private final VakService vakService;
    private final OpleidingService opleidingService;

    private final VakRestService vakRestService;

    public VakRestController(UserService userService,
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
        this.vakRestService = new VakRestService(
                userService,
                userRolService,
                studentVakService,
                docentVakService,
                vakService,
                opleidingService
        );
    }

    @PostMapping(value = "/list",
            consumes = "application/json",
            produces = "application/json")
    public String listVakken(@RequestBody JsonNode jsonNode) throws JsonProcessingException {
        return vakRestService.listVakken(jsonNode);
    }
}
