package com.example.cinema.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {
    @GetMapping("/")
    public String login() { return "login"; }

    @PostMapping("/login")
    public String verificare(@RequestParam String username, @RequestParam String password, Model model) {
        if("admin".equals(username) && "1234".equals(password)) {
            model.addAttribute("mesaj", "Login reușit!");
            return "redirect:/admin";
        } else {
            model.addAttribute("mesaj", "Parolă greșită!");
            return "login";
        }
    }
}
