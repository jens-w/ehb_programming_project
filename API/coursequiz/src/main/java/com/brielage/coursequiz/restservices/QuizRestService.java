package com.brielage.coursequiz.restservices;

import com.brielage.coursequiz.domain.Antwoord;
import com.brielage.coursequiz.domain.Hoofdstuk;
import com.brielage.coursequiz.domain.Quiz;
import com.brielage.coursequiz.domain.QuizVraag;
import com.brielage.coursequiz.domain.Rol;
import com.brielage.coursequiz.domain.User;
import com.brielage.coursequiz.domain.Vraag;
import com.brielage.coursequiz.domain.VraagMeerdereMultipleChoice;
import com.brielage.coursequiz.domain.VraagMultipleChoice;
import com.brielage.coursequiz.jsonintermediates.JsonQuiz;
import com.brielage.coursequiz.jsonintermediates.JsonVraag;
import com.brielage.coursequiz.services.AntwoordService;
import com.brielage.coursequiz.services.DocentVakService;
import com.brielage.coursequiz.services.HoofdstukService;
import com.brielage.coursequiz.services.QuizService;
import com.brielage.coursequiz.services.QuizVraagService;
import com.brielage.coursequiz.services.UserRolService;
import com.brielage.coursequiz.services.UserService;
import com.brielage.coursequiz.services.VakService;
import com.brielage.coursequiz.services.VraagService;
import com.brielage.coursequiz.singleton.APIResponse;
import com.brielage.coursequiz.singleton.ResponseLogger;
import com.brielage.coursequiz.singleton.Tools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class QuizRestService {
    private final UserService userService;
    private final UserRolService userRolService;
    private final VakService vakService;
    private final DocentVakService docentVakService;
    private final HoofdstukService hoofdstukService;
    private final QuizService quizService;
    private final VraagService vraagService;
    private final AntwoordService antwoordService;
    private final QuizVraagService quizVraagService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public QuizRestService(UserService userService,
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
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public String createQuiz(JsonNode jsonNode)
            throws JsonProcessingException {
        // LOG
        ResponseLogger.logRequest("quiz.create", jsonNode.toPrettyString());

        //TODO chapter stuff

        try {
            JsonQuiz jsonQuiz = objectMapper.treeToValue(jsonNode, JsonQuiz.class);

            if (!Tools.userExists(jsonQuiz.getUserkey(), userService))
                return APIResponse.respond(false, "andere");

            User u = userService.findByUserkey(jsonQuiz.getUserkey()).get(0);

            if (!Tools.isDocent(u, userRolService))
                return APIResponse.respond(false, "rechten_ongeldig");

            if (!Tools.doesVakExist(jsonQuiz.getVakid(), vakService))
                return APIResponse.respond(false, "vakid_bestaat_niet");

            if (!Tools.isDocentVanVak(u, jsonQuiz.getVakid(), docentVakService))
                return APIResponse.respond(false, "rechten_ongeldig");

            String quizNaam = jsonQuiz.getNaam();

            if (quizNaam == null ||
                    quizNaam.isBlank() ||
                    quizNaam.isEmpty())
                return APIResponse.respond(false, "quiznaam_ongeldig");
            else {
                if (!jsonQuiz.checkNaam())
                    return APIResponse.respond(false, "quiznaam_ongeldig");

                List<Quiz> quizListByNaam = quizService.findByNaam(quizNaam);

                if (quizListByNaam.size() > 0)
                    return APIResponse.respond(false, "quiznaam_bestaat_al");
            }

            String quizOmschrijving = jsonQuiz.getOmschrijving();

            if (quizOmschrijving == null ||
                    quizOmschrijving.isBlank() ||
                    quizOmschrijving.isEmpty())
                return APIResponse.respond(false, "omschrijving_ongeldig");

            if (jsonQuiz.getHoofdstuknummer() < 0)
                return APIResponse.respond(false, "hoofdstuknummer_ongeldig");

            if (jsonQuiz.getHoofdstuknummer() > 0)
                /*
                if (!Tools.doesHoofdstukExist(jsonQuiz.getHoofdstukid(), hoofdstukService))
                    return APIResponse.respond(false, "hoofdstukid_bestaat_niet");
                 */
                return APIResponse.respond(false, "hoofdstuk_bestaat_niet");

            List<Hoofdstuk> hoofdstukListByVakId =
                    hoofdstukService.findByVakId(jsonQuiz.getVakid());
            //TODO change this to actually use hoofdstuk
            Quiz quiz = new Quiz(
                    quizNaam,
                    quizOmschrijving,
                    jsonQuiz.isBeschikbaar(),
                    jsonQuiz.getVakid(),
                    hoofdstukListByVakId.get(0).getId());

            quizService.create(quiz);
            Rol rol = userRolService.findByUserId(u.getId()).get().getRol();

            return APIResponse.respond(true, rol);
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

    @SuppressWarnings({"OptionalGetWithoutIsPresent", "rawtypes"})
    public String createQuestion(JsonNode jsonNode)
            throws JsonProcessingException {
        // LOG
        ResponseLogger.logRequest("quiz.createQuestion", jsonNode.toPrettyString());

        //TODO hoofdstuk stuff?

        try {
            // stuff
            JsonVraag jsonVraag = objectMapper.treeToValue(jsonNode, JsonVraag.class);

            if (!Tools.userExists(jsonVraag.getUserkey(), userService))
                return APIResponse.respond(false, "andere");

            User u = userService.findByUserkey(jsonVraag.getUserkey()).get(0);

            if (!Tools.isDocent(u, userRolService))
                return APIResponse.respond(false, "rechten_ongeldig");

            long quizid = jsonVraag.getQuizid();

            if (quizid < 0)
                return APIResponse.respond(false, "quizid_ongeldig");

            long hoofdstukid = jsonVraag.getHoofdstukid();

            if (hoofdstukid < 0)
                return APIResponse.respond(false, "hoofdstukid_ongeldig");

            Optional<Quiz> optionalQuiz = Optional.empty();

            if (quizid > 0) {
                if (!Tools.doesQuizExist(quizid, quizService))
                    return APIResponse.respond(false, "quizid_bestaat_niet");

                Quiz q = quizService.findById(quizid).get();

                if (!Tools.isDocentVanVakVanQuiz(u, docentVakService, q))
                    return APIResponse.respond(false, "rechten_ongeldig");

                optionalQuiz = Optional.of(q);
            }

            Hoofdstuk hoofdstuk = null;

            if (hoofdstukid > 0) {
                if (!Tools.doesHoofdstukExist(hoofdstukid, hoofdstukService))
                    return APIResponse.respond(false, "hoofdstukid_bestaat_niet");
                hoofdstuk = hoofdstukService.findById(hoofdstukid).get();
            } else if (optionalQuiz.isPresent()) {
                hoofdstuk = hoofdstukService.findById(optionalQuiz.get().getHoofdstukid()).get();
            }

            if (!Tools.isDocentVanHoofdstuk(u, docentVakService, hoofdstuk))
                return APIResponse.respond(false, "rechten_ongeldig");

            String vraag = jsonVraag.getVraag();

            if (vraag == null ||
                    vraag.isBlank() ||
                    vraag.isEmpty())
                return APIResponse.respond(false, "vraag_ongeldig");

            if (Tools.doesVraagExist(vraag, vraagService))
                return APIResponse.respond(false, "vraag_bestaat_al");

            long aantalAntwoordenTonen = jsonVraag.getAantal_antwoorden_tonen();
            long minimumAantalJuisteAntwoordenTonen =
                    jsonVraag.getMinimum_aantal_juiste_antwoorden_tonen();

            if (aantalAntwoordenTonen < 1 ||
                    aantalAntwoordenTonen < minimumAantalJuisteAntwoordenTonen)
                return APIResponse.respond(false, "aantal_antwoorden_tonen_ongeldig");

            if (minimumAantalJuisteAntwoordenTonen < 0)
                return APIResponse.respond(false, "minimum_aantal_juiste_antwoorden_tonen_ongeldig");

            boolean isSimpelevraag = jsonVraag.isSimpele_vraag();
            long aantalJuisteAntwoordenNodig = jsonVraag.getAantal_juiste_antwoorden_nodig();

            if ((isSimpelevraag && aantalJuisteAntwoordenNodig != 0) ||
                    (!isSimpelevraag && aantalJuisteAntwoordenNodig < 1))
                return APIResponse.respond(false, "aantal_juiste_antwoorden_nodig_ongeldig");

            if ((isSimpelevraag && minimumAantalJuisteAntwoordenTonen != 0) ||
                    (!isSimpelevraag && minimumAantalJuisteAntwoordenTonen < 1))
                return APIResponse.respond(false, "minimum_aantal_juiste_antwoorden_tonen_ongeldig");

            boolean juisteAntwoordTonen = jsonVraag.isJuiste_antwoord_tonen();

            //TODO better error?
            if (!isSimpelevraag && juisteAntwoordTonen)
                return APIResponse.respond(false, "vraag_ongeldig");


            List antwoordenFromJson = jsonVraag.getAntwoorden();
            List<Antwoord> antwoorden = new ArrayList<>();

            for (Object antwoord : antwoordenFromJson) {
                Map y = (Map) antwoord;

                if (y.get("antwoord") == null)
                    return APIResponse.respond(false, "antwoord_leeg");

                String antwoordInhoud = (String) y.get("antwoord");

                if (antwoordInhoud == null ||
                        antwoordInhoud.isEmpty() ||
                        antwoordInhoud.isBlank())
                    return APIResponse.respond(false, "antwoord_leeg");

                if (y.get("isjuist") == null)
                    return APIResponse.respond(false, "isjuist_leeg");

                boolean isJuist = (boolean) y.get("isjuist");

                antwoorden.add(new Antwoord(
                        0,
                        antwoordInhoud,
                        isJuist));
            }

            Vraag v = null;

            if (isSimpelevraag)
                v = new VraagMultipleChoice(
                        vraag,
                        aantalAntwoordenTonen,
                        juisteAntwoordTonen,
                        hoofdstuk.getId()
                );
            else
                v = new VraagMeerdereMultipleChoice(
                        vraag,
                        aantalAntwoordenTonen,
                        false,
                        hoofdstuk.getId(),
                        aantalJuisteAntwoordenNodig,
                        minimumAantalJuisteAntwoordenTonen
                );

            try {
                vraagService.create(v);

                Vraag gemaakteVraag = vraagService.findByVraag(v.getVraag()).get(0);

                for (Antwoord a : antwoorden) {
                    a.setVraagId(gemaakteVraag.getId());
                    antwoordService.create(a);
                }

                if (optionalQuiz.isPresent())
                    quizVraagService.create(new QuizVraag(optionalQuiz.get().getId(), gemaakteVraag.getId()));
            } catch (Exception e) {
                e.printStackTrace();
                return APIResponse.respond(false, "other");
            }

            return APIResponse.respond(true, userRolService.findByUserId(u.getId()).get().getRol());
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

    public String listQuiz(JsonNode jsonNode)
            throws JsonProcessingException {
        // LOG
        ResponseLogger.logRequest("quiz.list", jsonNode.toPrettyString());

        //TODO chapter stuff

        try {
            JsonQuiz jsonQuiz = objectMapper.treeToValue(jsonNode, JsonQuiz.class);

            //LOG
            logger.info(jsonQuiz.toString());

            if (!Tools.userExists(jsonQuiz.getUserkey(), userService))
                return APIResponse.respond(false, "andere");

            User u = userService.findByUserkey(jsonQuiz.getUserkey()).get(0);

            long vakid = jsonQuiz.getVakid();

            if (vakid < 1)
                return APIResponse.respond(false, "vakid_ongeldig");

            if (!Tools.doesVakExist(vakid, vakService))
                return APIResponse.respond(false, "vakid_bestaat_niet");

            List<Quiz> quizList = quizService.findByVakId(vakid);

            if (quizList.size() < 1)
                return APIResponse.respond(false, "geen_quizzen_gevonden");

            return APIResponse.respondQuiz(
                    true,
                    userRolService.findByUserId(u.getId()).get().getRol(),
                    vakService.findById(vakid).get().getNaam(),
                    quizList);
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
}
