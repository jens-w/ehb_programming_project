package ehb.programmingproject.coursequiz.Reactions;

import java.util.HashMap;

public class Emojis {
    public static HashMap<Integer, String> abcs = new HashMap<Integer, String>();
    public static HashMap<String, String> signs = new HashMap<String, String>();

    static{
        abcs.put(0, "\uD83C\uDDE6");
        abcs.put(1, "\uD83C\uDDE7");
        abcs.put(2, "\uD83C\uDDE8");
        abcs.put(3, "\uD83C\uDDE9");
        abcs.put(4, "\uD83C\uDDEA");
        abcs.put(5, "\uD83C\uDDEB");
        abcs.put(6, "\uD83C\uDDEC");
        abcs.put(7, "\uD83C\uDDED");
        abcs.put(8, "\uD83C\uDDEE");

        signs.put("arrow", "➡");
        signs.put("stop", "⛔");
    }
}
