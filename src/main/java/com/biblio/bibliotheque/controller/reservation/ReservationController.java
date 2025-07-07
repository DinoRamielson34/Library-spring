package com.biblio.bibliotheque.controller.reservation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biblio.bibliotheque.model.livre.Type;
import com.biblio.bibliotheque.model.pret.Pret;
import com.biblio.bibliotheque.model.reservation.Reservation;
import com.biblio.bibliotheque.repository.gestion.AdherentRepository;
import com.biblio.bibliotheque.repository.livre.ExemplaireRepository;
import com.biblio.bibliotheque.repository.livre.TypeRepository;
import com.biblio.bibliotheque.repository.reservation.ReservationRepository;
import com.biblio.bibliotheque.service.pret.PretService;
import com.biblio.bibliotheque.service.pret.ProlongementService;
import com.biblio.bibliotheque.service.sanction.SanctionService;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Autowired
    private AdherentRepository adherentRepository;

    @Autowired
    private SanctionService sanctionService;

    @Autowired
    private PretService pretService;

    @Autowired
    private ProlongementService prolongementService;

    @Autowired
    private TypeRepository typeRepository;

    @GetMapping
    public String list(Model model) {
        List<Reservation> reservations = reservationRepository.findAll();
        model.addAttribute("reservations", reservations);
        return "views/reservation/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("exemplaires", exemplaireRepository.findAll());
        model.addAttribute("adherents", adherentRepository.findAll());
        return "views/reservation/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Reservation reservation, Model model) {
        Integer idAdherent = reservation.getAdherent().getIdAdherent();
        LocalDateTime dateReservation = reservation.getDate_reservation().atStartOfDay();
        LocalDate dateReserv = reservation.getDate_reservation();

        // Vérifier si l'adhérent est sanctionné
        boolean isSanctioned = sanctionService.isAdherentSanctioned(idAdherent, dateReservation);

        if (isSanctioned) {
            model.addAttribute("reservation", reservation);
            model.addAttribute("exemplaires", exemplaireRepository.findAll());
            model.addAttribute("adherents", adherentRepository.findAll());
            model.addAttribute("message", "❌ L'adhérent est actuellement sanctionné. Réservation refusée.");
            return "views/reservation/add";
        }

        // Vérifier si l'adhérent a des prêts prolongés actifs
        boolean hasActiveProlongements = prolongementService.hasActiveProlongements(idAdherent);

        if (hasActiveProlongements) {
            int prolongementsCount = prolongementService.getActiveProlongementsCount(idAdherent);
            model.addAttribute("reservation", reservation);
            model.addAttribute("exemplaires", exemplaireRepository.findAll());
            model.addAttribute("adherents", adherentRepository.findAll());
            model.addAttribute("message", "❌ L'adhérent a " + prolongementsCount + " prêt(s) prolongé(s) encore actif(s). Réservation refusée.");
            return "views/reservation/add";
        }

        reservation.setDate_reservation(dateReserv);
        reservation.setDate_debut_reservation(dateReserv);
        reservation.setDate_fin_reservation(dateReserv.plusDays(7));

        reservationRepository.save(reservation);
        return "redirect:/reservation";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de réservation invalide : " + id));
        model.addAttribute("reservation", reservation);
        model.addAttribute("exemplaires", exemplaireRepository.findAll());
        model.addAttribute("adherents", adherentRepository.findAll());
        return "views/reservation/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") Integer id, @ModelAttribute Reservation reservation) {
        reservation.setId_reservation(id);
        reservationRepository.save(reservation);
        return "redirect:/reservation";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        reservationRepository.deleteById(id);
        return "redirect:/reservation";
    }

    @GetMapping("/validate/{id}")
    public String validateReservation(@PathVariable("id") Integer id, Model model) {
        try {
            // Récupérer la réservation
            Reservation reservation = reservationRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("ID de réservation invalide : " + id));

            // Vérifier si l'adhérent est sanctionné
            Integer idAdherent = reservation.getAdherent().getIdAdherent();
            LocalDateTime dateValidation = LocalDateTime.now();
            
            // Vérifier si l'adhérent a des prêts prolongés actifs
            boolean hasActiveProlongements = prolongementService.hasActiveProlongements(idAdherent);
            
            if (hasActiveProlongements) {
                int prolongementsCount = prolongementService.getActiveProlongementsCount(idAdherent);
                model.addAttribute("errorMessage", "❌ L'adhérent a " + prolongementsCount + " prêt(s) prolongé(s) encore actif(s). Validation refusée.");
                return "redirect:/reservation?error=prolongements";
            }

            boolean isSanctioned = sanctionService.isAdherentSanctioned(idAdherent, dateValidation);
            
            if (isSanctioned) {
                model.addAttribute("errorMessage", "❌ L'adhérent est actuellement sanctionné. Validation refusée.");
                return "redirect:/reservation?error=sanctioned";
            }

            // Récupérer le type avec ID = 1
            Type type = typeRepository.findById(1)
                    .orElseThrow(() -> new IllegalArgumentException("Type avec ID 1 introuvable"));

            // Créer un nouveau prêt
            Pret pret = new Pret();
            pret.setAdherent(reservation.getAdherent());
            pret.setExemplaire(reservation.getExemplaire());
            pret.setType(type);
            pret.setDate_debut(reservation.getDate_debut_reservation());
            pret.setDate_fin(reservation.getDate_fin_reservation());

            // Sauvegarder le prêt
            pretService.savePret(pret);

            // Supprimer la réservation validée
            reservationRepository.deleteById(id);

            return "redirect:/reservation?success=validated";

        } catch (Exception e) {
            return "redirect:/reservation?error=validation";
        }
    }
}