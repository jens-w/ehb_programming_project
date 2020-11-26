public class Quiz{
    private int id, vakId;
    private boolean isBeschikbaar;

    public Quiz(int id, boolean isBeschikbaar, int vakId){
        this.id = id;
        this.isBeschikbaar = isBeschikbaar; //heeft de docent de quiz beschikbaar gemaakt
        this.vakId = vakId;
    }
}
