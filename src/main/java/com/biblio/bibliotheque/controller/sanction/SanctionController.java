
package com.biblio.bibliotheque.controller.sanction;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.biblio.bibliotheque.service.sanction.SanctionService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/sanction")
public class SanctionController {

    private final SanctionService sanctionService;

    @Autowired
    public SanctionController(SanctionService sanctionService) {
        this.sanctionService = sanctionService;
    }

    @GetMapping("/form")
    public String showForm() {
        return "views/sanction_form"; 
    }

    @PostMapping("/sanctionner")
    public String sanctionnerAdherent(
        @RequestParam Integer num_adherent,
        @RequestParam String date_debut,
        @RequestParam String date_fin,
        @RequestParam String motif,
        Model model
    ) {
        try {
            LocalDate debut = LocalDate.parse(date_debut);
            LocalDate fin = LocalDate.parse(date_fin);

            sanctionService.sanctionnerAdherent(num_adherent, debut, fin, motif);
            model.addAttribute("message", "Sanction enregistrée avec succès !");
        } catch (Exception e) {
            model.addAttribute("error", "Erreur : " + e.getMessage());
        }
        return "views/sanction_form";
    }
}

