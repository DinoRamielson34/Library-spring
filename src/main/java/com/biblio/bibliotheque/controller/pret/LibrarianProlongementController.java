package com.biblio.bibliotheque.controller.pret;

import com.biblio.bibliotheque.model.pret.StatusProlongement;
import com.biblio.bibliotheque.service.pret.LibrarianProlongementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/librarian/prolongement")
public class LibrarianProlongementController {

    @Autowired
    private LibrarianProlongementService librarianProlongementService;

    @GetMapping
    public String listPendingProlongationRequests(Model model) {
        List<StatusProlongement> pendingRequests = librarianProlongementService.getPendingProlongationRequests();
        model.addAttribute("pendingRequests", pendingRequests);
        return "librarian/prolongement/list";
    }

    @PostMapping("/accept/{id}")
    public String acceptProlongationRequest(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            librarianProlongementService.acceptProlongationRequest(id);
            redirectAttributes.addFlashAttribute("success", "Demande de prolongation acceptée.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/librarian/prolongement";
    }

    @PostMapping("/refuse/{id}")
    public String refuseProlongationRequest(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            librarianProlongementService.refuseProlongationRequest(id);
            redirectAttributes.addFlashAttribute("success", "Demande de prolongation refusée.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/librarian/prolongement";
    }
}
