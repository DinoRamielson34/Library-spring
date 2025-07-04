package com.biblio.bibliotheque.controller.gestion;

import com.biblio.bibliotheque.model.gestion.Utilisateur;
import com.biblio.bibliotheque.model.gestion.Role;
import com.biblio.bibliotheque.repository.gestion.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/utilisateur")
public class UtilisateurController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public String list(Model model) {
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
        model.addAttribute("utilisateurs", utilisateurs);
        return "views/utilisateur/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("utilisateur", new Utilisateur());
        model.addAttribute("roles", roleRepository.findAll());
        return "views/utilisateur/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Utilisateur utilisateur) {
        utilisateurRepository.save(utilisateur);
        return "redirect:/utilisateur";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID utilisateur invalide : " + id));
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("roles", roleRepository.findAll());
        return "views/utilisateur/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") Integer id, @ModelAttribute Utilisateur utilisateur) {
        utilisateur.setId_utilisateur(id);
        utilisateurRepository.save(utilisateur);
        return "redirect:/utilisateur";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        utilisateurRepository.deleteById(id);
        return "redirect:/utilisateur";
    }
}
