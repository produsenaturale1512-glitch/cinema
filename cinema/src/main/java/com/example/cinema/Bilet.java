package cinema.model;
public class Bilet {
    private int id;
    private Client client;
    private ProgramFilm programFilm;
    private int rand;
    private int loc;
    public Bilet() {}
    public Bilet(int id, Client client, ProgramFilm programFilm, int rand, int loc){
        this.id=id;this.client=client;this.programFilm=programFilm;this.rand=rand;this.loc=loc;
    }
    public Bilet(Client client, ProgramFilm programFilm, int rand, int loc){
        this(0,client,programFilm,rand,loc);
    }
    public int getId(){return id;} public Client getClient(){return client;} public ProgramFilm getProgramFilm(){return programFilm;}
    public int getRand(){return rand;} public int getLoc(){return loc;}
}
