package com.programmingproject.coursequiz;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import discord4j.core.DiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.Embed;
import discord4j.core.object.entity.Message;
import discord4j.core.object.reaction.ReactionEmoji;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.rest.util.Color;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
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

    public static void displayCommandsEmbedded(){


        Message msg = Bot.channel.createEmbed(spec ->
            spec.setColor(Color.SUMMER_SKY)
                        .setTitle("!help")
                        .addField("!registreer", "Discord account koppelen met de CourseQuiz website", false)
                        .addField("!vakken", "Welke vakken heb ik?", false)
                        .addField("!vraag <vak>", "Geeft een random vraag van een vak", false)
                        .addField("!vraag <vak> <hoofdstuk>", "Geeft een random vraag van een hoofdstuk", false)
                        ).block();

        //TESTING
        msg.addReaction(ReactionEmoji.unicode("\uD83C\uDDE6")).block();
        msg.addReaction(ReactionEmoji.unicode("\uD83C\uDDE7")).block();
        msg.addReaction(ReactionEmoji.unicode("\uD83C\uDDE8")).block();
        msg.addReaction(ReactionEmoji.unicode("\uD83C\uDDE9")).block();
        //
        msg.addReaction(ReactionEmoji.unicode("➡")).block();
        msg.addReaction(ReactionEmoji.unicode("⛔")).block();
    }

    public static void testVraag(String vak){

        //---API
        String id = "21";
        String question = "yadadadadadadada yadadada ydadad aydaaada ydadadada?";
        int amountAnswers = 4;
        String[] reactions = {"\uD83C\uDDE6", "\uD83C\uDDE7", "\uD83C\uDDE8", "\uD83C\uDDE9", "\uD83C\uDDEA", "\uD83C\uDDEB"};
        //---

        Message msg = Bot.channel.createEmbed(spec ->{
            spec.setColor(Color.SUMMER_SKY)
                    .setAuthor("Vraag " + id +" "+ vak, null, null)
                    .setTitle(question);

            for(var i=0;i<amountAnswers;i++){
                spec.addField(reactions[i], "rhgehrkug", false);
            }

                }).block();

        //FOR LOOP!!!!!
        msg.addReaction(ReactionEmoji.unicode("\uD83C\uDDE6")).block();
        msg.addReaction(ReactionEmoji.unicode("\uD83C\uDDE7")).block();
        msg.addReaction(ReactionEmoji.unicode("\uD83C\uDDE8")).block();
        msg.addReaction(ReactionEmoji.unicode("\uD83C\uDDE9")).block();
    }





    //JSON dummydata readers
    {
//        try {
//            //Gson gson = new Gson();
//            Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/dummydata/user.json"));
//            //com.programmingproject.coursequiz.User user = gson.fromJson(reader, com.programmingproject.coursequiz.User.class);
//            User.userList = new Gson().fromJson(reader, new TypeToken<List<User>>() {}.getType());
//            reader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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
