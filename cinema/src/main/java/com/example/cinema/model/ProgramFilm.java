package com.example.cinema.model;
import java.time.LocalDate;
import java.time.LocalTime;
public class ProgramFilm {
    private int id;
    private Film film;
    private Sala sala;
    private LocalDate data;
    private LocalTime oraInceput;
    public ProgramFilm() {}
    public ProgramFilm(int id, Film film, Sala sala, LocalDate data, LocalTime oraInceput){
        this.id=id;this.film=film;this.sala=sala;this.data=data;this.oraInceput=oraInceput;
    }
    public ProgramFilm(Film film, Sala sala, LocalDate data, LocalTime oraInceput){
        this(0,film,sala,data,oraInceput);
    }
    public ProgramFilm(LocalDate data, LocalTime oraInceput){
        this(0,null,null,data,oraInceput);
    }
    public int getId(){return id;} public Film getFilm(){return film;} public Sala getSala(){return sala;}
    public LocalDate getData(){return data;} public LocalTime getOra(){return oraInceput;}
}
