package com.example.cinema.controllers;

import com.example.cinema.model.Film;
import com.example.cinema.model.ProgramFilm;
import com.example.cinema.model.Sala;
import com.example.cinema.util.DBOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public String adminHome(Model model) {
        model.addAttribute("filme", DBOperations.getAllFilme());
        model.addAttribute("sali", DBOperations.getAllSali());
        return "admin";
    }

    @GetMapping("/film/add")
    public String addFilmForm() { return "addFilm"; }

    @PostMapping("/film/add")
    public String addFilm(@RequestParam String titlu, @RequestParam String gen, @RequestParam int durata, @RequestParam int popularitate) {
        DBOperations.insertFilm(new Film(titlu, gen, durata, popularitate));
        return "redirect:/admin";
    }

    @GetMapping("/sala/add")
    public String addSalaForm() { return "addSala"; }

    @PostMapping("/sala/add")
    public String addSala(@RequestParam String nume, @RequestParam int randuri, @RequestParam int locuriPeRand) {
        DBOperations.insertSala(new Sala(nume, randuri, locuriPeRand));
        return "redirect:/admin";
    }

    @GetMapping("/program/add")
    public String addProgramForm(Model model) {
        model.addAttribute("filme", DBOperations.getAllFilme());
        model.addAttribute("sali", DBOperations.getAllSali());
        return "addProgram";
    }

    @PostMapping("/program/add")
    public String addProgram(@RequestParam int filmId,
                             @RequestParam int salaId,
                             @RequestParam String data,
                             @RequestParam String ora) {
        ProgramFilm pf = new ProgramFilm(java.time.LocalDate.parse(data), java.time.LocalTime.parse(ora));
        DBOperations.insertProgramFilm(pf, filmId, salaId);
        return "redirect:/program";
    }
}
