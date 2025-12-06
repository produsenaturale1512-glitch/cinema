package cinema.model;
public class Client {
    private int id;
    private String nume;
    private String email;
    public Client() {}
    public Client(int id, String nume, String email){this.id=id;this.nume=nume;this.email=email;}
    public Client(String nume, String email){this(0,nume,email);}
    public int getId(){return id;} public void setId(int id){this.id=id;}
    public String getNume(){return nume;} public String getEmail(){return email;}
}
