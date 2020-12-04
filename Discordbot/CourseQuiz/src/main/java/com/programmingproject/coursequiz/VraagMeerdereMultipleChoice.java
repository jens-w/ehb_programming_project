package com.programmingproject.coursequiz;

public class VraagMeerdereMultipleChoice {
    private int id, aantalJuisteAntwoordenNodig, minimumAantalJuisteAntwoordenTonen, vraagId;

    public VraagMeerdereMultipleChoice(int id, int aantalJuisteAntwoordenNodig, int minimumAantalJuisteAntwoordenTonen, int vraagId){
        this.id = id;
        this.aantalJuisteAntwoordenNodig =aantalJuisteAntwoordenNodig;
        this.minimumAantalJuisteAntwoordenTonen = minimumAantalJuisteAntwoordenTonen;
        this.vraagId = vraagId;
    }
}
