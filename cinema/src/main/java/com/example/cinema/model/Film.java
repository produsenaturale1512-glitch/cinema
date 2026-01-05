package com.example.cinema.model;
public class Film {
    private int id;
    private String titlu;
    private String gen;
    private int durata;
    private int popularitate;
    public Film() {}
    public Film(int id, String titlu, String gen, int durata, int popularitate) {
        this.id = id; this.titlu = titlu; this.gen = gen; this.durata = durata; this.popularitate = popularitate;
    }
    public Film(String titlu, String gen, int durata, int popularitate) {
        this(0,titlu,gen,durata,popularitate);
    }
    public int getId(){return id;} public void setId(int id){this.id=id;}
    public String getTitlu(){return titlu;} public String getGen(){return gen;}
    public int getDurata(){return durata;} public int getPopularitate(){return popularitate;}
}
