public class Antwoord {
    private int id, isJuist, vraagId;
    private String antwoord;

    public Antwoord(int id, String antwoord, int isJuist, int vraagId){
        this.id = id;
        this.antwoord = antwoord;
        this.isJuist = isJuist;
        this.vraagId = vraagId;
    }
}
