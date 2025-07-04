package com.biblio.bibliotheque.controller.pret;

import com.biblio.bibliotheque.model.pret.TypeExemplairePret;
import com.biblio.bibliotheque.model.pret.TypeExemplairePretId;
import com.biblio.bibliotheque.model.livre.Exemplaire;
import com.biblio.bibliotheque.model.livre.Type;
import com.biblio.bibliotheque.repository.pret.*;
import com.biblio.bibliotheque.repository.livre.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/type-exemplaire")
public class TypeExemplairePretController {

    @Autowired
    private TypeExemplairePretRepository typeExemplairePretRepository;

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Autowired
    private TypeRepository typeRepository;

    @GetMapping
    public String list(Model model) {
        List<TypeExemplairePret> list = typeExemplairePretRepository.findAll();
        model.addAttribute("relations", list);
        return "views/typeexemplaire/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("relation", new TypeExemplairePret());
        model.addAttribute("exemplaires", exemplaireRepository.findAll());
        model.addAttribute("types", typeRepository.findAll());
        return "views/typeexemplaire/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute TypeExemplairePret relation) {
        typeExemplairePretRepository.save(relation);
        return "redirect:/type-exemplaire";
    }

    @GetMapping("/delete/{idExemplaire}/{idType}")
    public String delete(@PathVariable("idExemplaire") Integer idExemplaire,
                         @PathVariable("idType") Integer idType) {
        TypeExemplairePretId id = new TypeExemplairePretId(idExemplaire, idType);
        typeExemplairePretRepository.deleteById(id);
        return "redirect:/type-exemplaire";
    }
}
