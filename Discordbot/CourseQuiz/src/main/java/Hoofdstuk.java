import discord4j.core.event.domain.message.MessageCreateEvent;

import java.util.ArrayList;
import java.util.List;

public class Hoofdstuk {
    private int id, vakId;
    private String titel;
    private float nummer;

    public Hoofdstuk(int id, String titel, float nummer, int vakId){
        this.id = id;
        this.titel = titel;
        this.nummer = nummer;
        this.vakId = vakId;
    }

    public String getTitel() {
        return titel;
    }

    public int getVakId() {
        return vakId;
    }

    public static void printHoofdstukkenByVakId(MessageCreateEvent event, int id){
        String hoofdstukken = "";
        Vak vak = Vak.getVakByVakId(id);
        for(Hoofdstuk hoofdstuk : hoofdstukList){
            if(id == hoofdstuk.getVakId()) {
                hoofdstukken += "!quiz " + vak.getNaam() + " " + hoofdstuk.getTitel() + System.lineSeparator();
            }
        }
        Bot.botSendMessage(event, hoofdstukken);
    }

    public static List<Hoofdstuk> hoofdstukList = new ArrayList<>();
}
