package ehb.programmingproject.coursequiz.Commands;

import discord4j.rest.util.Color;
import ehb.programmingproject.coursequiz.API.APIReader;
import ehb.programmingproject.coursequiz.Bot;
import ehb.programmingproject.coursequiz.Models.User;

import java.util.List;

public class Vakken {
    public static void Display(User user){
        List<String> vakken = APIReader.GetVakken(user.getUserkey());
        String msg =  "";
        for (String s : vakken) {
            msg += s + "\n";
        }
        final String finalMsg = msg;

        Bot.MessageEvent.getMessage().getChannel().block().createEmbed(spec ->{
            spec.setColor(Color.SUMMER_SKY)
                    .setTitle("Jouw vakken:")
                    .setDescription(finalMsg);
        }).block();
    }
}
