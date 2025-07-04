package com.biblio.bibliotheque.controller.gestion;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biblio.bibliotheque.model.gestion.RegleJourFerie;
import com.biblio.bibliotheque.repository.gestion.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/regle-jourferie")
public class RegleJourFerieController {

    @Autowired
    private RegleJourFerieRepository regleJourFerieRepository;

    @GetMapping
    public String list(Model model) {
        List<RegleJourFerie> regles = regleJourFerieRepository.findAll();
        model.addAttribute("regles", regles);
        return "views/reglejourferie/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("regleJourFerie", new RegleJourFerie());
        return "views/reglejourferie/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute RegleJourFerie regleJourFerie) {
        regleJourFerie.setDateModif(LocalDateTime.now());
        regleJourFerieRepository.save(regleJourFerie);
        return "redirect:/regle-jourferie";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        RegleJourFerie regle = regleJourFerieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID non valide : " + id));
        model.addAttribute("regleJourFerie", regle);
        return "views/reglejourferie/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") Integer id, @ModelAttribute RegleJourFerie regleJourFerie) {
        regleJourFerie.setId_regle_jour_ferie(id);
        regleJourFerie.setDateModif(LocalDateTime.now());
        regleJourFerieRepository.save(regleJourFerie);
        return "redirect:/regle-jourferie";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        regleJourFerieRepository.deleteById(id);
        return "redirect:/regle-jourferie";
    }
}
