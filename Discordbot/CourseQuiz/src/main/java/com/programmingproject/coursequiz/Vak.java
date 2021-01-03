package com.programmingproject.coursequiz;

import discord4j.core.event.domain.message.MessageCreateEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Vak {
    private int id, opleidingId;
    private String naam;
    //private int jaar;

    public Vak(){}

    public Vak(int id, int opleidingId, String naam){
        this.id = id;
        this.opleidingId = opleidingId;
        this.naam = naam;
    }

    public int getId() {
        return id;
    }

    public int getOpleidingId() {
        return opleidingId;
    }

    public String getNaam() {
        return naam;
    }

    public static Vak getVakByVakId(int id) {
        for(Vak vak : vakList){
            if(vak.getId() == id){
                return vak;
            }
        }
        return null;
    }

    public static void displayVakken(MessageCreateEvent event, User user) throws Exception {

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://api.brielage.com:8081/user/login/");
        httpPost.setEntity(new StringEntity("{\"email\":\"jos.dewolf@outlook.com\", \"password\":\"abc12345\"}", ContentType.APPLICATION_JSON));
        String response = new BasicResponseHandler().handleResponse(client.execute(httpPost));
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(response);
        JSONArray jsonArray = (JSONArray) jsonObject.get("vakken");
        Iterator iterator = jsonArray.iterator();

        BotUtil.botSendMessage(event, jsonObject.get("userkey").toString());

        while(iterator.hasNext()) {
            JSONObject vak = (JSONObject) jsonArray.get(jsonArray.indexOf(iterator.next()));
            BotUtil.botSendMessage(event, vak.get("naam").toString());
        }
        client.close();
    }

    public static List<Vak> vakList = new ArrayList<>();
}
