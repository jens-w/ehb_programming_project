package com.programmingproject.coursequiz;

import discord4j.core.event.domain.message.MessageCreateEvent;

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
                    Command.displayCommands(event);
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

    public static void botSendMessage(MessageCreateEvent event, String content){
        event.getMessage().getChannel().block().createMessage(content).block();
    }

    public static void executeVraagCommand(MessageCreateEvent event, User user, String[] messageSplit){
        if(messageSplit.length==2){//!vraag serveros
            //Geeft een random vraag van een vak
        }else if (messageSplit.length > 2){//!vraag serveros hoofdstuk
            //Geeft een random vraag uit een hoofdstuk van een vak
        }

    }
}
