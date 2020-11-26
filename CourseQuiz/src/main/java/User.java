import discord4j.core.event.domain.message.MessageCreateEvent;
import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String userkey;
    private String snowflake;

    public User(){}

    public User(int id, String userkey, String snowflake){
        this.id = id;
        this.userkey = userkey;
        this.snowflake = snowflake;
    }

    public void setUserkey(String userkey) {
        this.userkey = userkey;
    }

    public String getUserkey() {
        return userkey;
    }

    public void setSnowflake(String snowflake) {
        this.snowflake = snowflake;
    }

    public String getSnowflake() {
        return this.snowflake;
    }

    public User getUserByUserKey(String userkey){
        for(User user : userList){
            if(user.userkey.equals(userkey)){
                return user;
            }
        }
        return null;
    }

    public User getUserBySnowflake(String snowflake){
        for(User user : userList){
            if(user.snowflake.equals(snowflake)){
                return user;
            }
        }
        return null;
    }

    public void registerUser(MessageCreateEvent event, User user, String[] commandSplit, String snowflake){
        if(user.getUserByUserKey(commandSplit[2]) != null){
            user = user.getUserByUserKey(commandSplit[2]);
            user.setSnowflake(snowflake);
            Bot.botSendMessage(event, user.getSnowflake());//just to test if set correctly
        }else {
            Bot.botSendMessage(event, "User key not found.");
        }
    }

    public static List<User> userList = new ArrayList<>();
}
