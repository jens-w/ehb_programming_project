package ehb.programmingproject.coursequiz.Reactions;

import discord4j.core.event.domain.message.ReactionAddEvent;
import ehb.programmingproject.coursequiz.Commands.Vraag;
import ehb.programmingproject.coursequiz.Models.User;

public class Reaction {
    public static void Read(ReactionAddEvent event, User user) {
        String reaction = event.getEmoji().asUnicodeEmoji().get().getRaw();
        String author = event.getMessage().block().getEmbeds().get(0).getAuthor().get().getName().get();
        String[] authorSplit = author.split(" ");

        if (user.isRegistered()) {

            if (authorSplit[1].equals("vraag")) {
                Vraag.GradeQuestion(event, reaction, authorSplit[0], authorSplit[2], user);
            }

    //        if (APIReader.GetVakken(user.getUserkey()).contains(authorSplit[0])){}
        }
    }
}
