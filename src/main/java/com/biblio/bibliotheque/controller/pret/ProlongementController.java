package com.biblio.bibliotheque.controller.pret;

import com.biblio.bibliotheque.model.pret.Prolongement;
import com.biblio.bibliotheque.repository.pret.ProlongementRepository;
import com.biblio.bibliotheque.service.pret.ProlongementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/prolongement")
public class ProlongementController {

    @Autowired
    private ProlongementRepository prolongementRepository;

    @Autowired
    private ProlongementService prolongementService;

    @GetMapping
    public String listProlongements(Model model) {
        List<Prolongement> prolongements = prolongementRepository.findAll();
        model.addAttribute("prolongements", prolongements);
        return "views/prolongement/list";
    }

    @PostMapping("/demander/{id}")
    public String demanderProlongement(@PathVariable("id") Integer id, 
                                       @RequestParam("dateFinDemandee") String dateFinDemandeeStr, 
                                       RedirectAttributes redirectAttributes) {
        try {
            LocalDateTime dateFinDemandee = java.time.LocalDate.parse(dateFinDemandeeStr).atStartOfDay();
            prolongementService.demanderProlongement(id, dateFinDemandee);
            redirectAttributes.addFlashAttribute("success", "Demande de prolongation enregistrée.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/preter/liste";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", ex.getMessage());
        return "redirect:/preter/liste";
    }
}