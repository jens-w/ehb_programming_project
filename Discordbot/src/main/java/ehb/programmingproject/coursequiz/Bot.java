package ehb.programmingproject.coursequiz;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.event.domain.message.ReactionAddEvent;
import ehb.programmingproject.coursequiz.Commands.Command;
import ehb.programmingproject.coursequiz.DB.DBReader;
import ehb.programmingproject.coursequiz.Models.User;
import ehb.programmingproject.coursequiz.Reactions.Reaction;

public class Bot {
    public static MessageCreateEvent MessageEvent = null;
    public static ReactionAddEvent ReactionEvent = null;

    public static void main(String[] args){
        GatewayDiscordClient client = DiscordClientBuilder
                .create("Nzc3ODMyODM2ODk1NjA0NzM2.X7JLRw.BVz91isx-VeHA9e5nTrD0uqd0do")
                .build()
                .login()
                .block();

        //New message
        client.getEventDispatcher().on(MessageCreateEvent.class).subscribe(event -> {
            if(!event.getMessage().getAuthor().get().isBot()){
                String message = event.getMessage().getContent();

                if(message.startsWith("!")){
                    MessageEvent = event;
                    DBReader dbReader = new DBReader();
                    Long snowflake = MessageEvent.getMessage().getAuthor().get().getId().asLong();
                    User user = dbReader.GetUser(snowflake);
                    if(user.getUserkey() == null){user = new User(snowflake);}
                    Command.Read(message, user);
                }
            }
        });

        //User reaction
        client.getEventDispatcher().on(ReactionAddEvent.class).subscribe(event -> {
            if (!event.getUser().block().isBot()
                    && event.getMessage().block().getAuthor().get().getId().equals(client.getSelfId())
                    && !event.getMessage().block().getEmbeds().isEmpty()){

                ReactionEvent = event;
                DBReader dbReader = new DBReader();
                Long snowflake = ReactionEvent.getUserId().asLong();
                User user = dbReader.GetUser(snowflake);
                if(user.getUserkey() == null){user = new User(snowflake);}
                Reaction.Read(ReactionEvent, user);
            }
        });

        client.onDisconnect().block();
    }

    public static void SendMessage(String message){
        if(MessageEvent != null) {
            MessageEvent.getMessage().getChannel().block().createMessage(message).block();
        } else if(ReactionEvent != null){
            ReactionEvent.getChannel().block().createMessage(message).block();
        }
    }

    public static void SendPrivateMessage(String message){
        if(MessageEvent != null) {
            MessageEvent.getMessage().getAuthor().get().getPrivateChannel().block().createMessage(message).block();
        } else if(ReactionEvent != null){
            ReactionEvent.getUser().block().getPrivateChannel().block().createMessage(message).block();
        }
    }
}
