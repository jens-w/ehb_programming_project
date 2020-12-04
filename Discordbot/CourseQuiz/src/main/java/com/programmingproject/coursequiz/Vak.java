package com.programmingproject.coursequiz;

import java.util.ArrayList;
import java.util.List;

public class Vak {
    private int id;
    private String naam;
    //private int jaar;
    //private int opleidingId;

    public Vak(){}

    public Vak(int id, String naam){
        this.id = id;
        this.naam = naam;
    }

    public int getId() {
        return id;
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

    public static List<Vak> vakList = new ArrayList<>();
}
