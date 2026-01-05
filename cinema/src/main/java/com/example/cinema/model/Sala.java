package com.example.cinema.model;
public class Sala {
    private int id;
    private String nume;
    private int randuri;
    private int locuriPeRand;
    public Sala() {}
    public Sala(int id, String nume, int randuri, int locuriPeRand){
        this.id=id; this.nume=nume; this.randuri=randuri; this.locuriPeRand=locuriPeRand;
    }
    public Sala(String nume,int randuri,int locuriPeRand){this(0,nume,randuri,locuriPeRand);}
    public int getId(){return id;} public void setId(int id){this.id=id;}
    public String getNume(){return nume;} public int getRanduri(){return randuri;} public int getLocuriPeRand(){return locuriPeRand;}
    public int getTotalLocuri(){return randuri * locuriPeRand;}
}
