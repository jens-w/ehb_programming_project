package ehb.programmingproject.coursequiz.Commands;

import discord4j.core.event.domain.message.ReactionAddEvent;
import discord4j.core.object.Embed;
import discord4j.core.object.entity.Message;
import discord4j.core.object.reaction.ReactionEmoji;
import discord4j.rest.util.Color;
import ehb.programmingproject.coursequiz.Bot;
import ehb.programmingproject.coursequiz.Models.User;
import ehb.programmingproject.coursequiz.Reactions.Emojis;

import java.util.List;

public class Vraag {
    public static void Read(String message){
        String[] messageSplit = message.split(" ");

        if(messageSplit.length==2){//!vraag <vak>
            CreateQuestion(messageSplit[1]);

        }else if (messageSplit.length > 2){//!vraag <vak> <hoofdstuk>
            //Geeft een random vraag uit een hoofdstuk van een vak
        }
    }

    public static void CreateQuestion(String vak){

        //------API
        String id = "7";
        String question = "Dit is een dummydata vraag?";
        String[] answers = {"Antwoord1", "Antwoord2", "Antwoord3", "Antwoord4"};
//        String correctAnswer = "Antwoord2";
        //-------

        String author = vak + " vraag " + id;

        Message msg = Bot.MessageEvent.getMessage().getChannel().block().createEmbed(spec ->{
            spec.setColor(Color.SUMMER_SKY)
                    .setAuthor(author, null, null)
                    .setTitle(question);

            for (var i=0;i<answers.length;i++) {
                spec.addField(Emojis.abcs.get(i), answers[i], false);
            }

        }).block();

        for (var i=0;i<answers.length;i++) {
            msg.addReaction(ReactionEmoji.unicode(Emojis.abcs.get(i))).block();
        }
    }


    public static void GradeQuestion(ReactionAddEvent event, String reaction, String vak, String vraagId, User user) {

        //------API
        //Stuur vak + vraagId door naar API om het juiste antwoord te krijgen.
        String correctAnswer = "Antwoord2";
        //

        List<Embed.Field> answers = event.getMessage().block().getEmbeds().get(0).getFields();
        String correctEmoji = "";

        for (Embed.Field answer : answers) {
            if (answer.getValue().equals(correctAnswer)) {
                correctEmoji = answer.getName();
            }
        }

        final String finalCorrectEmoji = correctEmoji;

        Bot.SendMessage("<@!" + user.getSnowflake() + ">");
        Message msg = Bot.MessageEvent.getMessage().getChannel().block().createEmbed(spec -> {
            if (finalCorrectEmoji.equals(reaction)) {
                spec.setColor(Color.GREEN)
                        .setTitle("Correct!");
            } else {
                spec.setColor(Color.RED)
                        .setTitle("Fout!");
            }
        }).block();
    }
}
