package com.biblio.bibliotheque.controller.reservation;

import com.biblio.bibliotheque.model.reservation.StatutReservation;

import com.biblio.bibliotheque.repository.pret.*;
import com.biblio.bibliotheque.repository.gestion.*;
import com.biblio.bibliotheque.repository.livre.*;
import com.biblio.bibliotheque.repository.reservation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/statut-reservation")
public class StatutReservationController {

    @Autowired
    private StatutReservationRepository statutReservationRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private StatutRepository statutRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("statutsReservation", statutReservationRepository.findAll());
        return "views/statutReservation/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("statutReservation", new StatutReservation());
        model.addAttribute("reservations", reservationRepository.findAll());
        model.addAttribute("statuts", statutRepository.findAll());
        return "views/statutReservation/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute StatutReservation statutReservation) {
        statutReservation.setDate_modif(LocalDateTime.now());
        statutReservationRepository.save(statutReservation);
        return "redirect:/statut-reservation";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        StatutReservation sr = statutReservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID invalide : " + id));
        model.addAttribute("statutReservation", sr);
        model.addAttribute("reservations", reservationRepository.findAll());
        model.addAttribute("statuts", statutRepository.findAll());
        return "views/statutReservation/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") Integer id, @ModelAttribute StatutReservation statutReservation) {
        statutReservation.setId_statut_reservation(id);
        statutReservation.setDate_modif(LocalDateTime.now());
        statutReservationRepository.save(statutReservation);
        return "redirect:/statut-reservation";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        statutReservationRepository.deleteById(id);
        return "redirect:/statut-reservation";
    }
}
