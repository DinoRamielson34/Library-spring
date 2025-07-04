package com.biblio.bibliotheque.repository.reservation;

import com.biblio.bibliotheque.model.livre.Exemplaire;
import com.biblio.bibliotheque.model.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    boolean existsByExemplaire(Exemplaire exemplaire);
}
