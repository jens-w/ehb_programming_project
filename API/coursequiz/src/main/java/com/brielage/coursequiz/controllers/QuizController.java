package com.brielage.coursequiz.controllers;

import com.brielage.coursequiz.restservices.QuizRestService;
import com.brielage.coursequiz.services.AntwoordService;
import com.brielage.coursequiz.services.DocentVakService;
import com.brielage.coursequiz.services.HoofdstukService;
import com.brielage.coursequiz.services.QuizService;
import com.brielage.coursequiz.services.QuizVraagService;
import com.brielage.coursequiz.services.UserRolService;
import com.brielage.coursequiz.services.UserService;
import com.brielage.coursequiz.services.VakService;
import com.brielage.coursequiz.services.VraagService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
@RestController
@RequestMapping(value = "/quiz")
public class QuizController {
    private final UserService userService;
    private final UserRolService userRolService;
    private final VakService vakService;
    private final DocentVakService docentVakService;
    private final HoofdstukService hoofdstukService;
    private final QuizService quizService;
    private final VraagService vraagService;
    private final AntwoordService antwoordService;
    private final QuizVraagService quizVraagService;

    private final QuizRestService quizRestService;

    public QuizController(UserService userService,
                          UserRolService userRolService,
                          VakService vakService,
                          DocentVakService docentVakService,
                          HoofdstukService hoofdstukService,
                          QuizService quizService,
                          VraagService vraagService,
                          AntwoordService antwoordService,
                          QuizVraagService quizVraagService) {
        this.userService = userService;
        this.userRolService = userRolService;
        this.vakService = vakService;
        this.docentVakService = docentVakService;
        this.hoofdstukService = hoofdstukService;
        this.quizService = quizService;
        this.vraagService = vraagService;
        this.antwoordService = antwoordService;
        this.quizVraagService = quizVraagService;
        this.quizRestService = new QuizRestService(
                userService,
                userRolService,
                vakService,
                docentVakService,
                hoofdstukService,
                quizService,
                vraagService,
                antwoordService,
                quizVraagService
        );
    }

    @PostMapping(value = "/create",
            consumes = "application/json",
            produces = "application/json")
    public String createQuiz(@RequestBody JsonNode jsonNode)
            throws JsonProcessingException {
        return quizRestService.createQuiz(jsonNode);
    }

    @PostMapping(value = "/create/question",
            consumes = "application/json",
            produces = "application/json")
    public String createQuestion(@RequestBody JsonNode jsonNode)
            throws JsonProcessingException {
        return quizRestService.createQuestion(jsonNode);
    }

    @PostMapping(value = "/list",
            consumes = "application/json",
            produces = "application/json")
    public String listQuiz(@RequestBody JsonNode jsonNode)
            throws JsonProcessingException {
        return quizRestService.listQuiz(jsonNode);
    }

    @PostMapping(value = "/get",
            consumes = "application/json",
            produces = "application/json")
    public String getQuiz(@RequestBody JsonNode jsonNode)
            throws JsonProcessingException {
        return quizRestService.getQuiz(jsonNode);
    }

    @PostMapping(value = "/submit",
            consumes = "application/json",
            produces = "application/json")
    public String submitQuiz(@RequestBody JsonNode jsonNode)
            throws JsonProcessingException {
        return quizRestService.submitQuiz(jsonNode);
    }
}
