package com.programmingproject.coursequiz;

import discord4j.core.event.domain.message.MessageCreateEvent;
import java.util.ArrayList;
import java.util.List;

public class User {
    private int id, opleidingId;
    private String userkey;
    private String snowflake;

    public User(){}

    public User(int id, int opleidingId, String userkey, String snowflake){
        this.id = id;
        this.opleidingId = opleidingId;
        this.userkey = userkey;
        this.snowflake = snowflake;
    }

    public void setUserkey(String userkey) {
        this.userkey = userkey;
    }

    public String getUserkey() {
        return userkey;
    }

    public void setOpleidingId(int opleidingId) {
        this.opleidingId = opleidingId;
    }

    public int getOpleidingId() {
        return opleidingId;
    }

    public void setSnowflake(String snowflake) {
        this.snowflake = snowflake;
    }

    public String getSnowflake() {
        return this.snowflake;
    }

    public boolean isRegistered() {
        return this.getSnowflake() != null;
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

    public void registerUser(MessageCreateEvent event, User user, String[] messageSplit, String snowflake){

        if(messageSplit.length > 1){
            if(user.getUserByUserKey(messageSplit[1]) != null){
                user = user.getUserByUserKey(messageSplit[1]);
                user.setSnowflake(snowflake);
                BotUtil.botSendMessage(event, user.getSnowflake());//just to test if set correctly
            }else{
                event.getMessage().getAuthor().get().getPrivateChannel().block().createMessage("User key niet gevonden.").block();
            }
        }
        else{
            event.getMessage().getAuthor().get().getPrivateChannel().block().createMessage("Typ hier '!registreer <user key>' (Jouw user key is terug te vinden op de CourseQuiz website)").block();
        }
    }

    public static List<User> userList = new ArrayList<>();
}
