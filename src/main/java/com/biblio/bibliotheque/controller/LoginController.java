package com.biblio.bibliotheque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.biblio.bibliotheque.service.gestion.UtilisateurService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/")
    public String loginForm() {
        return "login/login"; 
    }

    @GetMapping("/login")
    public String logout() {
        return "login/login"; 
    }



    @PostMapping("/login")
    public String login(@RequestParam String username,
            @RequestParam String mdp,
            Model model,
            HttpSession session) {
        return utilisateurService.login(username, mdp).map(utilisateur -> {
            session.setAttribute("user", utilisateur);
            session.setAttribute("role", utilisateur.getRole().getNom());
            if ("bibliothecaire".equals(utilisateur.getRole().getNom())) {
                return "redirect:/librarian/home";
            } else {
                return "redirect:/adherent/home";
            }
        }).orElseGet(() -> {
            model.addAttribute("error", "Nom d'utilisateur ou mot de passe incorrect");
            return "login/login";
        });
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
