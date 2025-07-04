package com.biblio.bibliotheque.controller.pret;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.biblio.bibliotheque.model.pret.Pret;
import com.biblio.bibliotheque.model.pret.Rendu;
import com.biblio.bibliotheque.repository.pret.PretRepository;
import com.biblio.bibliotheque.repository.pret.RenduRepository;
import com.biblio.bibliotheque.service.gestion.AdherentService;
import com.biblio.bibliotheque.service.gestion.JourFerieService;
import com.biblio.bibliotheque.service.gestion.RegleJourFerieService;
import com.biblio.bibliotheque.service.pret.PretService;
import com.biblio.bibliotheque.service.pret.RenduService;

@Controller
@RequestMapping("/rendu")
public class RenduController {

    @Autowired
    private RenduRepository renduRepository;

    @Autowired
    private PretRepository pretRepository;

    @Autowired
    private AdherentService adherentService;

    @Autowired
    private PretService pretService;

    @Autowired
    private JourFerieService jourFerieService;

    @Autowired
    private RegleJourFerieService regleJourFerieService;

    @Autowired
    private RenduService renduService;

    

    @GetMapping("/retour")
    public String PageRetour(Model model) {
        model.addAttribute("retour", "Bienvenue dans la page retour de livre");
        return "views/rendu/retour_livre";
    }

    @GetMapping("/valider")
    public String traiterFormulaire(
            @RequestParam("idAdherent") Integer idAdherent,
            @RequestParam("idPret") Integer idPret,
            Model model) {
        try {
            String message = renduService.validerRendu(idAdherent, idPret);
            model.addAttribute("message", message);
            if (message.startsWith("Erreur")) {
                model.addAttribute("messageType", "error");
            } else {
                model.addAttribute("messageType", "success");
            }
        } catch (Exception e) {
            model.addAttribute("message", "Erreur inattendue lors de la vérification.");
            model.addAttribute("messageType", "error");
            e.printStackTrace();
        }

        return "views/rendu/retour_livre";
    }

}
