package com.biblio.bibliotheque.repository.reservation;

import com.biblio.bibliotheque.model.reservation.StatutReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatutReservationRepository extends JpaRepository<StatutReservation, Integer> {
    // Par exemple :
    // List<StatutReservation> findByReservationId(Integer idReservation);
}
