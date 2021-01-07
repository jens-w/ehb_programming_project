package ehb.programmingproject.coursequiz.Commands;

import ehb.programmingproject.coursequiz.Bot;
import ehb.programmingproject.coursequiz.Models.User;

public class Command {

    public static void Read(String message, User user){
        String[] messageSplit = message.split(" ");
        boolean userAllowed = true;

        if(!user.isRegistered()){
            if(messageSplit[0].equals("!vakken") || messageSplit[0].equals("!vraag")){
                Bot.SendMessage("Account moet geregistreerd zijn!");
                userAllowed = false;
            }
        }

        if(userAllowed) {
            switch (messageSplit[0].toLowerCase()) {
                case "!help":
                    Help.Display();
                    break;
                case "!registreer":
                    Registreer.Execute(message, user);
                    break;
                case "!vakken":
                    Vakken.Display(user);
                    break;
                case "!vraag":
                    Vraag.Read(message);
                    break;
            }
        }
    }
}
