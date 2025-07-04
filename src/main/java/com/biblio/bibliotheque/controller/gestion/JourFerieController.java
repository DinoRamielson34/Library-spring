package com.biblio.bibliotheque.controller.gestion;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biblio.bibliotheque.model.gestion.JourFerie;

import com.biblio.bibliotheque.model.gestion.JourFerie;
import com.biblio.bibliotheque.repository.gestion.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/jourferie")
public class JourFerieController {

    @Autowired
    private JourFerieRepository jourFerieRepository;

    @GetMapping
    public String list(Model model) {
        List<JourFerie> jours = jourFerieRepository.findAll();
        model.addAttribute("joursFeries", jours);
        return "views/jourferie/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("jourFerie", new JourFerie());
        return "views/jourferie/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute JourFerie jourFerie) {
        jourFerieRepository.save(jourFerie);
        return "redirect:/jourferie";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        JourFerie jour = jourFerieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ID: " + id));
        model.addAttribute("jourFerie", jour);
        return "views/jourferie/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") Integer id, @ModelAttribute JourFerie jourFerie) {
        jourFerie.setId_jour_ferie(id);
        jourFerieRepository.save(jourFerie);
        return "redirect:/jourferie";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        jourFerieRepository.deleteById(id);
        return "redirect:/jourferie";
    }
}
