package com.biblio.bibliotheque.service.reservation;

import com.biblio.bibliotheque.model.reservation.StatutReservation;
import com.biblio.bibliotheque.repository.reservation.StatutReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatutReservationService {

    private final StatutReservationRepository statutReservationRepository;

    @Autowired
    public StatutReservationService(StatutReservationRepository statutReservationRepository) {
        this.statutReservationRepository = statutReservationRepository;
    }

    // Récupérer tous les statuts
    public List<StatutReservation> getAllStatuts() {
        return statutReservationRepository.findAll();
    }

    // Récupérer un statut par ID
    public Optional<StatutReservation> getStatutById(Integer id) {
        return statutReservationRepository.findById(id);
    }

    // Enregistrer ou modifier un statut
    public StatutReservation saveStatut(StatutReservation statut) {
        return statutReservationRepository.save(statut);
    }

    // Supprimer un statut
    public void deleteStatut(Integer id) {
        statutReservationRepository.deleteById(id);
    }

    // (Optionnel) Récupérer les statuts par réservation
    // public List<StatutReservation> getStatutsByReservationId(Integer idReservation) {
    //     return statutReservationRepository.findByReservationId(idReservation);
    // }
}
