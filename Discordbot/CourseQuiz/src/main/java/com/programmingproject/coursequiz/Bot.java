package com.programmingproject.coursequiz;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.event.domain.message.ReactionAddEvent;
import discord4j.core.object.Embed;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.object.reaction.ReactionEmoji;
import discord4j.core.spec.EmbedCreateSpec;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

//https://github.com/Discord4J/Discord4J

public class Bot {
    public static MessageCreateEvent currentEvent;
    public static MessageChannel channel;
    public static String message;

    public static void main(String[] args){

        GatewayDiscordClient client = DiscordClientBuilder
                .create("Nzc3ODMyODM2ODk1NjA0NzM2.X7JLRw.BVz91isx-VeHA9e5nTrD0uqd0do")
                .build()
                .login()
                .block();

        client.getEventDispatcher().on(ReactionAddEvent.class).subscribe(event -> {
            if (!event.getUser().block().isBot()
                    && event.getMessage().block().getAuthor().get().getId().equals(client.getSelfId())
                    && !event.getMessage().block().getEmbeds().isEmpty()){


//                event.getChannel().block().createMessage(event.getMessage().block().getEmbeds().get(0).getDescription().toString()).block();
//                event.getChannel().block().createMessage(event.getMember().get().getDisplayName()).block();

                BotUtil.readReaction(event);

//                if(event.getEmoji().equals(ReactionEmoji.unicode("\uD83C\uDDE7"))){
//                    event.getChannel().block().createMessage("Correct!").block();
//                }else{
//                    event.getChannel().block().createMessage("False!").block();
//                }
            }
        });

        client.getEventDispatcher().on(MessageCreateEvent.class).subscribe(event -> {
                    if ( event.getMessage().getAuthor().map(user -> !user.isBot()).orElse(false))//if not a bot
                    {
                        currentEvent = event;
                        message = currentEvent.getMessage().getContent();
                        channel = currentEvent.getMessage().getChannel().block();


                        if(message.startsWith("!")){
                            String snowflake = event.getMessage().getAuthor().get().getId().asString();
//                            String server = event.getMessage().getGuildId().get().asString();
                            User user = new User();
                            if(user.getUserBySnowflake(snowflake) != null) {user = user.getUserBySnowflake(snowflake);}//checks if user is already linked
                            try {BotUtil.readMessage(event, user, message, snowflake);} catch (Exception e) {BotUtil.botSendMessage(event, e.getMessage());}
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
