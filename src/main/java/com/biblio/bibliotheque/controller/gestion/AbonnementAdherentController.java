package com.biblio.bibliotheque.controller.gestion;

import com.biblio.bibliotheque.model.gestion.AbonnementAdherent;
import com.biblio.bibliotheque.model.gestion.AbonnementAdherentId;

import com.biblio.bibliotheque.repository.gestion.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/delete/{idAdherent}/{idAbonnement}")
    public String delete(@PathVariable("idAdherent") Integer idAdherent,
                         @PathVariable("idAbonnement") Integer idAbonnement) {
        AbonnementAdherentId id = new AbonnementAdherentId(idAdherent, idAbonnement);
        abonnementAdherentRepository.deleteById(id);
        return "redirect:/abonnement-adherent";
    }
}
