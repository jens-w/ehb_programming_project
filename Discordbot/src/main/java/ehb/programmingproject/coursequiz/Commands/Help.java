package ehb.programmingproject.coursequiz.Commands;

import discord4j.rest.util.Color;
import ehb.programmingproject.coursequiz.Bot;

public class Help {
    public static void Display(){
        Bot.MessageEvent.getMessage().getChannel().block().createEmbed(spec ->
                spec.setColor(Color.SUMMER_SKY)
                        .setTitle("CourseQuiz Help")
                        .addField("!registreer", "Discord account koppelen met de CourseQuiz website", false)
                        .addField("!vakken", "Welke vakken heb ik?", false)
                        .addField("!vraag <vak>", "Geeft een random vraag van een vak", false)
                        .addField("!vraag <vak> <hoofdstuk>", "Geeft een random vraag van een hoofdstuk", false)
        ).block();
    }
}
