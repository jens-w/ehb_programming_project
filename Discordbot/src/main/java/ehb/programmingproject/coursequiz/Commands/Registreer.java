package ehb.programmingproject.coursequiz.Commands;

import ehb.programmingproject.coursequiz.API.APIReader;
import ehb.programmingproject.coursequiz.Bot;
import ehb.programmingproject.coursequiz.DB.DBWriter;
import ehb.programmingproject.coursequiz.Models.User;

public class Registreer {
    public static void Execute(String message, User user){
        String[] messageSplit = message.split(" ");

        if(messageSplit.length > 1){ //!registreer <user key>
            if(APIReader.UserkeyExist(messageSplit[1])){
                DBWriter dbWriter = new DBWriter();
                dbWriter.InsertUser(messageSplit[1], user.getSnowflake());
                Bot.SendPrivateMessage("Succesvol geregistreerd!");
            }else{
                Bot.SendPrivateMessage("User key niet gevonden.");
            }
        }
        else{ //!registreer
            Bot.SendPrivateMessage("Typ hier '!registreer <user key>' (Jouw user key is terug te vinden op de CourseQuiz website)");
        }
    }
}
