package com.programmingproject.coursequiz;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.event.domain.message.ReactionAddEvent;

import java.util.Objects;

public class BotUtil {

    public static void readMessage(MessageCreateEvent event, User user, String message, String snowflake) throws Exception {
        String[] messageSplit = message.split(" ");
        boolean userAllowed = true;

        if(!user.isRegistered()){
            if(messageSplit[0].equals("!vakken") || messageSplit[0].equals("!vraag")){
                botSendMessage(event, "Account moet geregistreerd zijn!");
                userAllowed = false;
            }
        }

        if(userAllowed) {
            switch (messageSplit[0]) {
                case "!help":
                    Command.displayCommandsEmbedded();
                    break;
                case "!registreer":
                    user.registerUser(event, user, message, snowflake);
                    break;
                case "!vakken":
                    Vak.displayVakken(event, user);
                    break;
                case "!vraag":
                    executeVraagCommand(event, user, messageSplit);
                    break;
                default:
                    botSendMessage(event, "Commando niet herkent, type !help voor meer info.");
            }
        }
    }

    public static void readReaction(ReactionAddEvent event){
        //API - get question + correct answer
        //
        String embedAuthor = event.getMessage().block().getEmbeds().get(0).getAuthor().get().getName().get();
        String[] authorSplit = embedAuthor.split(" ");

        if(authorSplit[0].equals("Vraag")){
            String vraagId = authorSplit[1];
            event.getChannel().block().createMessage(vraagId).block();
        }

//        int fields = event.getMessage().block().getEmbeds().get(0).getFields().size();
    }

    public static void botSendMessage(MessageCreateEvent event, String content){
        Bot.currentEvent.getMessage().getChannel().block().createMessage(content).block();
    }

    public static void executeVraagCommand(MessageCreateEvent event, User user, String[] messageSplit){


        if(messageSplit.length==2){//!vraag serveros

            Command.testVraag(messageSplit[1]);

        }else if (messageSplit.length > 2){//!vraag serveros hoofdstuk

            //Geeft een random vraag uit een hoofdstuk van een vak
        }

    }
}
