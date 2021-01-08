package com.brielage.coursequiz.controllers;

import com.brielage.coursequiz.restservices.QuizRestService;
import com.brielage.coursequiz.services.DocentVakService;
import com.brielage.coursequiz.services.HoofdstukService;
import com.brielage.coursequiz.services.QuizService;
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
@RequestMapping(value = "/quiz")
public class QuizController {
    private final UserService userService;
    private final UserRolService userRolService;
    private final VakService vakService;
    private final DocentVakService docentVakService;
    private final HoofdstukService hoofdstukService;
    private final QuizService quizService;

    private final QuizRestService quizRestService;

    public QuizController(UserService userService,
                          UserRolService userRolService,
                          VakService vakService,
                          DocentVakService docentVakService,
                          HoofdstukService hoofdstukService,
                          QuizService quizService) {
        this.userService = userService;
        this.userRolService = userRolService;
        this.vakService = vakService;
        this.docentVakService = docentVakService;
        this.hoofdstukService=hoofdstukService;
        this.quizService = quizService;
        this.quizRestService = new QuizRestService(
                userService,
                userRolService,
                vakService,
                docentVakService,
                hoofdstukService,
                quizService
        );
    }

    @PostMapping(value = "/create",
            consumes = "application/json",
            produces = "application/json")
    public String createQuiz(@RequestBody JsonNode jsonNode) throws JsonProcessingException {
        return quizRestService.createQuiz(jsonNode);
    }
}
