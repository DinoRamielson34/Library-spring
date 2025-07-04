package com.biblio.bibliotheque.controller.gestion;

import com.biblio.bibliotheque.model.gestion.Profil;
import com.biblio.bibliotheque.model.gestion.Regle;
import com.biblio.bibliotheque.repository.gestion.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/profil")
public class ProfilController {

    @Autowired
    private ProfilRepository profilRepository;

    @Autowired
    private RegleRepository regleRepository;

    @GetMapping
    public String list(Model model) {
        List<Profil> profils = profilRepository.findAll();
        model.addAttribute("profils", profils);
        return "views/profil/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("profil", new Profil());
        model.addAttribute("regles", regleRepository.findAll());
        return "views/profil/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Profil profil) {
        profilRepository.save(profil);
        return "redirect:/profil";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Profil profil = profilRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID profil invalide : " + id));
        model.addAttribute("profil", profil);
        model.addAttribute("regles", regleRepository.findAll());
        return "views/profil/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") Integer id, @ModelAttribute Profil profil) {
        profil.setId_profil(id);
        profilRepository.save(profil);
        return "redirect:/profil";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        profilRepository.deleteById(id);
        return "redirect:/profil";
    }
}
