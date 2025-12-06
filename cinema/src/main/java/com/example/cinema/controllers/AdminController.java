package cinema.controller;
import cinema.model.Film;
import cinema.model.Sala;
import cinema.util.DBOperations;
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
}
