import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import java.util.HashMap;
import java.util.Map;

public class Bot {
    public static void main(String[] args){
        GatewayDiscordClient client = DiscordClientBuilder
                .create("Nzc3ODMyODM2ODk1NjA0NzM2.X7JLRw.BVz91isx-VeHA9e5nTrD0uqd0do")
                .build()
                .login()
                .block();

        client.getEventDispatcher().on(MessageCreateEvent.class).subscribe(event -> {
                    if ( event.getMessage().getAuthor().map(user -> !user.isBot()).orElse(false))//checks if not a bot
                    {
                        String snowflake = event.getMessage().getAuthor().get().getId().asString();
                        String command = event.getMessage().getContent().toLowerCase();
                        String[] commandSplit = command.split(" ");
                        User user = new User();
                        if(user.getUserBySnowflake(snowflake) != null) {user = user.getUserBySnowflake(snowflake);}//checks if user is already linked
                        if(command.equals("!quiz")) {displayCommands(event);}//Displays all available commands.
                        else if(command.startsWith("!quiz ")) {executeCommand(event, user, command, commandSplit, snowflake);}
                    }
                });
        client.onDisconnect().block();
    }

    public static void executeCommand(MessageCreateEvent event, User user, String command, String[] commandSplit, String snowflake){

        if(command.startsWith("!quiz register ")){user.registerUser(event, user, commandSplit, snowflake); }

        else if(user.getSnowflake() != null) {//Only users that have registered can use these commands.
            for (final Map.Entry<String, Execute> entry : commands.entrySet()) {//!quiz <command> (for testing only right now)
                if (command.startsWith("!quiz " + entry.getKey())) {
                    entry.getValue().execute(event);
                    break;
                }
            }
            for(Vak vak : Vak.vakList){//!quiz <vak>
                if(commandSplit[1].equals(vak.getNaam().toLowerCase())){
                    Hoofdstuk.printHoofdstukkenByVakId(event, vak.getId());
                }
            }
        }
        else{botSendMessage(event, "Please register using your user key.");}
    }

    public static void botSendMessage(MessageCreateEvent event, String content){
        event.getMessage().getChannel().block().createMessage(content).block();
    }

    public static void displayCommands(MessageCreateEvent event) {
        for(Command command : Command.commandHelpList){
            Bot.botSendMessage(event, command.getHelp());
        }
    }

    //Commands map
    public static final Map<String, Execute> commands = new HashMap<>();
    //Populate the commands map
    static{
        for(Command command : Command.commandHelpList){
            commands.put(command.getCommand(), event -> event.getMessage()
                    .getChannel().block()
                    .createMessage(command.getOutput()).block());
        }
    }
}

interface Execute{
    void execute(MessageCreateEvent event);
}
