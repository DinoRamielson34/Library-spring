package com.biblio.bibliotheque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.biblio.bibliotheque.model.gestion.Utilisateur;
import com.biblio.bibliotheque.model.pret.Pret;
import com.biblio.bibliotheque.service.pret.PretService;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;

@Controller
public class HelloController {

    @GetMapping("/bienvenue")
    public String afficherHello(Model model) {
        model.addAttribute("message", "Bienvenue à la bibliothèque !");
        return "/views/hello";
    }

    @GetMapping("/hello")
    public String hello(HttpSession session, Model model) {
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("user");
        if (utilisateur == null) {
            return "redirect:/login";
        }

        String username = utilisateur.getUsername();
        String role = utilisateur.getRole().getNom();

        model.addAttribute("username", username);
        model.addAttribute("role", role);

        // Détermine si l'utilisateur est un adhérent
        boolean isAdherent = "adherent".equalsIgnoreCase(role);
        model.addAttribute("isAdherent", isAdherent);

        return "/views/hello";
    }

}
