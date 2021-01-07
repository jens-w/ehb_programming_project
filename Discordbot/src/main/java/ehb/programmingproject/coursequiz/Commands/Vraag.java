package ehb.programmingproject.coursequiz.Commands;

import discord4j.core.object.entity.Message;
import discord4j.core.object.reaction.ReactionEmoji;
import discord4j.rest.util.Color;
import ehb.programmingproject.coursequiz.Bot;
import ehb.programmingproject.coursequiz.Reactions.Emojis;

public class Vraag {
    public static void Read(String message){
        String[] messageSplit = message.split(" ");

        if(messageSplit.length==2){//!vraag <vak>
            Display(messageSplit[1]);

        }else if (messageSplit.length > 2){//!vraag <vak> <hoofdstuk>
            //Geeft een random vraag uit een hoofdstuk van een vak
        }
    }

    public static void Display(String vak){

        //------API
        String id = "7";
        String question = "Dit is een dummydata vraag?";
        String[] answers = {"Antwoord1", "Antwoord2", "Antwoord3", "Antwoord4"};
//        String correctAnswer = "Antwoord2";
        //-------

        CreateQuestion(vak + " vraag " + id, question, answers);
    }

    public static void Display(String vak, String hoofdstuk){}




    public static void CreateQuestion(String author, String question, String[] answers){
        Message msg = Bot.MessageEvent.getMessage().getChannel().block().createEmbed(spec ->{
            spec.setColor(Color.SUMMER_SKY)
                    .setAuthor(author, null, null)
                    .setTitle(question);

            for (var i=0;i<answers.length;i++) {
                spec.addField(Emojis.map.get(i), answers[i], false);
            }

        }).block();

        for (var i=0;i<answers.length;i++) {
            msg.addReaction(ReactionEmoji.unicode(Emojis.map.get(i))).block();
        }
    }
}
