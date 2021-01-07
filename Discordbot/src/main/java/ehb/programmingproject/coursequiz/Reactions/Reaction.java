package ehb.programmingproject.coursequiz.Reactions;

import discord4j.core.event.domain.message.ReactionAddEvent;
import ehb.programmingproject.coursequiz.Models.User;

public class Reaction {
    public static void Read(ReactionAddEvent event, User user){
        String reaction = event.getEmoji().asUnicodeEmoji().get().getRaw();
        String title = event.getMessage().block().getEmbeds().get(0).getTitle().get();
    }
}
