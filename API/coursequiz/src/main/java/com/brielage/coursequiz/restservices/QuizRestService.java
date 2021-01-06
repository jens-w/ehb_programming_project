package com.brielage.coursequiz.restservices;

import com.brielage.coursequiz.domain.Hoofdstuk;
import com.brielage.coursequiz.domain.JsonQuiz;
import com.brielage.coursequiz.domain.Quiz;
import com.brielage.coursequiz.services.DocentVakService;
import com.brielage.coursequiz.services.HoofdstukService;
import com.brielage.coursequiz.services.QuizService;
import com.brielage.coursequiz.services.UserRolService;
import com.brielage.coursequiz.services.UserService;
import com.brielage.coursequiz.services.VakService;
import com.brielage.coursequiz.singleton.APIResponse;
import com.brielage.coursequiz.singleton.Tools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class QuizRestService {
    private final UserService userService;
    private final UserRolService userRolService;
    private final VakService vakService;
    private final DocentVakService docentVakService;
    private final HoofdstukService hoofdstukService;
    private final QuizService quizService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public QuizRestService(UserService userService,
                           UserRolService userRolService,
                           VakService vakService,
                           DocentVakService docentVakService,
                           HoofdstukService hoofdstukService,
                           QuizService quizService) {
        this.userService = userService;
        this.userRolService = userRolService;
        this.vakService = vakService;
        this.docentVakService = docentVakService;
        this.hoofdstukService = hoofdstukService;
        this.quizService = quizService;
    }

    public String createQuiz(JsonNode jsonNode) throws JsonProcessingException {
        // LOG
        logRequest(jsonNode.toPrettyString());

        //TODO chapter stuff

        try {
            JsonQuiz jsonQuiz = objectMapper.treeToValue(jsonNode, JsonQuiz.class);

            // LOG
            logger.info("jsonQuiz: " + jsonQuiz.toString());

            if (!Tools.isDocent(jsonQuiz.getUserkey(), userService, userRolService))
                return APIResponse.respond(false, "rechten_ongeldig");

            if (!Tools.doesVakExist(jsonQuiz.getVakid(), vakService))
                return APIResponse.respond(false, "vakid_bestaat_niet");

            if (!Tools.isDocentVanVak(
                    jsonQuiz.getUserkey(),
                    jsonQuiz.getVakid(),
                    userService,
                    docentVakService))
                return APIResponse.respond(false, "rechten_ongeldig");

            if (jsonQuiz.getNaam() == null ||
                    jsonQuiz.getNaam().isBlank() ||
                    jsonQuiz.getNaam().isEmpty())
                return APIResponse.respond(false, "quiznaam_ongeldig");
            else {
                if (!jsonQuiz.checkNaam())
                    return APIResponse.respond(false, "quiznaam_ongeldig");

                List<Quiz> quizListByNaam = quizService.findByNaam(jsonQuiz.getNaam());

                if (quizListByNaam.size() > 0)
                    return APIResponse.respond(false, "quiznaam_bestaat_al");
            }

            if (jsonQuiz.getOmschrijving() == null ||
                    jsonQuiz.getOmschrijving().isBlank() ||
                    jsonQuiz.getOmschrijving().isEmpty())
                return APIResponse.respond(false, "omschrijving_ongeldig");

            if (jsonQuiz.getHoofdstuknummer() < 0)
                return APIResponse.respond(false, "hoofdstukid_ongeldig");

            if (jsonQuiz.getHoofdstuknummer() > 0)
                /*
                if (!Tools.doesHoofdstukExist(jsonQuiz.getHoofdstukid(), hoofdstukService))
                    return APIResponse.respond(false, "hoofdstukid_bestaat_niet");
                 */
                return APIResponse.respond(false, "hoofdstuk_bestaat_niet");

            List<Hoofdstuk> hoofdstukListByVakId =
                    hoofdstukService.findByVakId(jsonQuiz.getVakid());
            //TODO change this to actually use hoofdstuk
            Quiz quiz = new Quiz(jsonQuiz.getNaam(),
                    jsonQuiz.getOmschrijving(),
                    jsonQuiz.isBeschikbaar(),
                    jsonQuiz.getVakid(),
                    hoofdstukListByVakId.get(0).getId());

            logger.info(quiz.toString());

            quizService.create(quiz);

            return APIResponse.respond(true);
        } catch (UnrecognizedPropertyException | InvalidFormatException e) {
            e.printStackTrace();

            // LOG
            logger.error("\n" + e.getMessage());

            return APIResponse.respond(false, "veld_ongeldig");
        } catch (JsonProcessingException e) {
            e.printStackTrace();

            // LOG
            logger.error("\n" + e.getMessage());

            return APIResponse.respond(false, "andere");
        }
    }


    public void logRequest(String s) {
        logger.info("\nrequest:\n" + s);
    }


}
