package com.example.cinema.controllers;

import com.example.cinema.util.DBOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class PublicController {

    @GetMapping("/filme")
    public String listFilme(Model model,
                            @RequestParam(name = "gen", required = false) String gen,
                            @RequestParam(name = "sort", required = false) String sort) {
        model.addAttribute("filme", DBOperations.getFilmeFiltrate(gen, sort));
        model.addAttribute("genSelectat", gen);
        model.addAttribute("sortareSelectata", sort);
        return "filme";
    }

    @GetMapping("/program")
    public String listProgram(Model model) {
        model.addAttribute("program", DBOperations.getProgramFilme());
        return "program";
    }
}
