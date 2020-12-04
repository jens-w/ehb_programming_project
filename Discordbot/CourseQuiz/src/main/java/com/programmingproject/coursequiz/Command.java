package com.programmingproject.coursequiz;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Command {
    private String command, output, help;

    public Command(String help){
        this.help = help;
    }

    public Command(String command, String help){
        this.command = command;
        this.help = help;
    }

    public Command(String command, String output, String help){
        this.command = command;
        this.output = output;
        this.help = help;
    }

    public String getCommand() {
        return command;
    }

    public String getHelp() {
        return help;
    }

    public String getOutput() {
        return output;
    }

    public static final List<Command> commandHelpList = new ArrayList<>();
    static{
        commandHelpList.add(new Command("ping", "pong", "!quiz ping  ---  Hello bot? You there?"));
        commandHelpList.add(new Command("register", "!quiz register  ---  Register with your user key to link Discord account."));
        commandHelpList.add(new Command("!quiz <vak>  ---  Displays a list of the available quizzes for a particular course."));
    }

    public static final List<Command> vakHelpList = new ArrayList<>();
    static{
        for(Vak vak : Vak.vakList){
            vakHelpList.add(new Command("!quiz "+vak.getNaam()));
        }
    }



    //JSON dummydata readers
    {
        try {
            //Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/dummydata/user.json"));
            //com.programmingproject.coursequiz.User user = gson.fromJson(reader, com.programmingproject.coursequiz.User.class);
            User.userList = new Gson().fromJson(reader, new TypeToken<List<User>>() {}.getType());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    {
        try {
            Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/dummydata/vak.json"));
            Vak.vakList = new Gson().fromJson(reader, new TypeToken<List<Vak>>() {}.getType());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    {
        try {
            Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/dummydata/opleiding.json"));
            Opleiding.opleidingList = new Gson().fromJson(reader, new TypeToken<List<Opleiding>>() {}.getType());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    {
        try {
            Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/dummydata/hoofdstuk.json"));
            Hoofdstuk.hoofdstukList = new Gson().fromJson(reader, new TypeToken<List<Hoofdstuk>>() {}.getType());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
