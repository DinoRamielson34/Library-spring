package com.biblio.bibliotheque.controller.gestion;

import com.biblio.bibliotheque.model.gestion.Adherent;
import com.biblio.bibliotheque.repository.gestion.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/adherent")
public class AdherentController {

    @Autowired
    private AdherentRepository adherentRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private ProfilRepository profilRepository;

    @GetMapping
    public String listAdherents(Model model) {
        List<Adherent> adherents = adherentRepository.findAll();
        model.addAttribute("adherents", adherents);
        return "views/adherent/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("adherent", new Adherent());
        model.addAttribute("utilisateurs", utilisateurRepository.findAll());
        model.addAttribute("profils", profilRepository.findAll());
        return "views/adherent/add";
    }

    @PostMapping("/add")
    public String addAdherent(@ModelAttribute Adherent adherent) {
        adherentRepository.save(adherent);
        return "redirect:/adherent";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Adherent adherent = adherentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ID: " + id));
        model.addAttribute("adherent", adherent);
        model.addAttribute("utilisateurs", utilisateurRepository.findAll());
        model.addAttribute("profils", profilRepository.findAll());
        return "views/adherent/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateAdherent(@PathVariable("id") Integer id, @ModelAttribute Adherent adherent) {
        adherent.setIdAdherent(id);
        adherentRepository.save(adherent);
        return "redirect:/adherent";
    }

    @GetMapping("/delete/{id}")
    public String deleteAdherent(@PathVariable("id") Integer id) {
        adherentRepository.deleteById(id);
        return "redirect:/adherent";
    }
}
