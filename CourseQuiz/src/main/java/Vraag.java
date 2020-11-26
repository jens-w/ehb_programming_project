public class Vraag {
    private int id, aantalAntwoordenTonen, juisteAntwoordTonen, hoofdstukId;
    private String vraag;

    public Vraag(int id, String vraag, int aantalAntwoordenTonen, int juisteAntwoordTonen, int hoofdstukId){
        this.id = id;
        this.vraag = vraag;
        this.aantalAntwoordenTonen = aantalAntwoordenTonen;
        this.juisteAntwoordTonen = juisteAntwoordTonen;
        this.hoofdstukId =hoofdstukId;
    }
}
