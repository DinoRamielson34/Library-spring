package com.biblio.bibliotheque.controller.gestion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biblio.bibliotheque.model.gestion.AbonnementAdherent;
import com.biblio.bibliotheque.repository.gestion.AbonnementAdherentRepository;
import com.biblio.bibliotheque.repository.gestion.AbonnementRepository;
import com.biblio.bibliotheque.repository.gestion.AdherentRepository;

@Controller
@RequestMapping("/abonnement-adherent")
public class AbonnementAdherentController {

    @Autowired
    private AbonnementAdherentRepository abonnementAdherentRepository;

    @Autowired
    private AdherentRepository adherentRepository;

    @Autowired
    private AbonnementRepository abonnementRepository;

    @GetMapping
    public String list(Model model) {
        List<AbonnementAdherent> records = abonnementAdherentRepository.findAll();
        model.addAttribute("abonnementsAdherents", records);
        return "views/abonnementadherent/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("abonnementAdherent", new AbonnementAdherent());
        model.addAttribute("adherents", adherentRepository.findAll());
        model.addAttribute("abonnements", abonnementRepository.findAll());
        return "views/abonnementadherent/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute AbonnementAdherent abonnementAdherent) {
        abonnementAdherentRepository.save(abonnementAdherent);
        return "redirect:/abonnement-adherent";
    }

}
