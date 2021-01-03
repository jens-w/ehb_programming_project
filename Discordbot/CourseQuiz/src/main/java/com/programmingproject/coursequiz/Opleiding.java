package com.programmingproject.coursequiz;

import java.util.ArrayList;
import java.util.List;

public class Opleiding {
    private int id;
    private String naam;

    public Opleiding(int id, String naam){
        this.id = id;
        this.naam = naam;
    }

    public static List<Opleiding> opleidingList = new ArrayList<>();
}
