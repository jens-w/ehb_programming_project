package com.programmingproject.coursequiz;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import java.util.HashMap;
import java.util.Map;

//https://github.com/Discord4J/Discord4J

public class Bot {
    public static void main(String[] args){

        GatewayDiscordClient client = DiscordClientBuilder
                .create("Nzc3ODMyODM2ODk1NjA0NzM2.X7JLRw.BVz91isx-VeHA9e5nTrD0uqd0do")
                .build()
                .login()
                .block();

        client.getEventDispatcher().on(MessageCreateEvent.class).subscribe(event -> {
                    if ( event.getMessage().getAuthor().map(user -> !user.isBot()).orElse(false))//if not a bot
                    {
                        String message = event.getMessage().getContent().toLowerCase();

                        if(message.startsWith("!")){
                            String snowflake = event.getMessage().getAuthor().get().getId().asString();
                            User user = new User();
                            if(user.getUserBySnowflake(snowflake) != null) {user = user.getUserBySnowflake(snowflake);}//checks if user is already linked
                            BotUtil.readMessage(event, user, message, snowflake);
                        }
                    }
                });
        client.onDisconnect().block();
    }

//    public static void executeCommand(MessageCreateEvent event, User user, String command, String[] commandSplit, String snowflake){
//
//        if(user.getSnowflake() != null) {//Only users that have registered can use these commands.
//            for (final Map.Entry<String, Execute> entry : commands.entrySet()) {//!quiz <command> (for testing only right now)
//                if (command.startsWith("!" + entry.getKey())) {
//                    entry.getValue().execute(event);
//                    break;
//                }
//            }
//            for(Vak vak : Vak.vakList){//!quiz <vak>
//                if(commandSplit[0].equals("!"+vak.getNaam().toLowerCase())){
//                    Hoofdstuk.printHoofdstukkenByVakId(event, vak.getId());
//                }
//            }
//        }
//    }
//
//    //Commands map
//    public static final Map<String, Execute> commands = new HashMap<>();
//    //Populate the commands map
//    static{
//        for(Command command : Command.commandHelpList){
//            commands.put(command.getCommand(), event -> event.getMessage()
//                    .getChannel().block()
//                    .createMessage(command.getOutput()).block());
//        }
//    }
}
//
//interface Execute{
//    void execute(MessageCreateEvent event);
//}
