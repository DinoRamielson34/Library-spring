package com.biblio.bibliotheque.repository.sanction;

import com.biblio.bibliotheque.model.sanction.Penalite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PenaliteRepository extends JpaRepository<Penalite, Integer> {
    // Ajoutez ici des méthodes de recherche si nécessaire
}
