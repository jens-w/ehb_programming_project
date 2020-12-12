package com.programmingproject.coursequiz;

import discord4j.core.event.domain.message.MessageCreateEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public static void displayVakken(MessageCreateEvent event, User user){
        for(Vak vak : vakList){
            if(user.getOpleidingId() == vak.getOpleidingId()){
                BotUtil.botSendMessage(event, vak.getNaam());
            }
        }
    }

    public static List<Vak> vakList = new ArrayList<>();
}
