package com.biblio.bibliotheque.service.reservation;

import com.biblio.bibliotheque.model.reservation.Reservation;
import com.biblio.bibliotheque.repository.reservation.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    // Récupérer toutes les réservations
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    // Récupérer une réservation par ID
    public Optional<Reservation> getReservationById(Integer id) {
        return reservationRepository.findById(id);
    }

    // Enregistrer ou modifier une réservation
    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    // Supprimer une réservation par ID
    public void deleteReservation(Integer id) {
        reservationRepository.deleteById(id);
    }

    // (Optionnel) Récupérer les réservations par adhérent
    // public List<Reservation> getReservationsByAdherentId(Integer idAdherent) {
    //     return reservationRepository.findByAdherentId(idAdherent);
    // }

    // (Optionnel) Récupérer les réservations par exemplaire
    // public List<Reservation> getReservationsByExemplaireId(Integer idExemplaire) {
    //     return reservationRepository.findByExemplaireId(idExemplaire);
    // }
}
