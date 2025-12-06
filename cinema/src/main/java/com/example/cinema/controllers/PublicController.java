package cinema.controller;
import cinema.model.Film;
import cinema.util.DBOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/filme")
public class PublicController {

    @GetMapping
    public String listFilme(Model model) {
        model.addAttribute("filme", DBOperations.getAllFilme());
        return "filme";
    }
}
