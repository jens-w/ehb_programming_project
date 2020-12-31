package com.programmingproject.coursequiz;

import discord4j.core.event.domain.message.MessageCreateEvent;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


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

    public User getUserByUserKey(MessageCreateEvent event, String userkey) throws Exception{
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://api.brielage.com:8081/test/userkey/");
        httpPost.setEntity(new StringEntity("{\"userkey\":\"" + userkey + "\"}", ContentType.APPLICATION_JSON));
        String response = new BasicResponseHandler().handleResponse(client.execute(httpPost));
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(response);

        if(jsonObject.get("success").equals(true)){
            for(User user : userList){
//                BotUtil.botSendMessage(event, jsonObject.get("success").toString());
                if(user.userkey.equals(userkey)){
                    return user;
                }
            }
        }
        return null;
    }

    public User getUserBySnowflake(String snowflake){
        String userkey = DbManager.SelectUserkeyWithSnowflake(snowflake);

        for(User user : userList){
            if(user.userkey.equals(userkey)){
                return user;
            }
        }
        return null;
    }

    public void registerUser(MessageCreateEvent event, User user, String message, String snowflake)throws Exception{
        String[] messageSplit = message.split(" ");
        if(messageSplit.length > 1){ //!registreer <user key>
            user = user.getUserByUserKey(event, messageSplit[1]);
            if(user != null){
                user.setSnowflake(snowflake);
                BotUtil.botSendMessage(event, user.getSnowflake());//just to test if set correctly
                DbManager.InsertUser(messageSplit[1], snowflake);
            }else{
                event.getMessage().getAuthor().get().getPrivateChannel().block().createMessage("User key niet gevonden.").block();
            }
        }


        else{ //!registreer
            event.getMessage().getAuthor().get().getPrivateChannel().block().createMessage("Typ hier '!registreer <user key>' (Jouw user key is terug te vinden op de CourseQuiz website)").block();
        }
    }

    public static List<User> userList = new ArrayList<>();
    static{
        userList.add(new User(5, 1, "rghRtk4lZmJLTsFJFBQ8", ""));
        userList.add(new User(6, 1, "ogIM23NkPlfdxeeGZeX0", ""));
    }
}
