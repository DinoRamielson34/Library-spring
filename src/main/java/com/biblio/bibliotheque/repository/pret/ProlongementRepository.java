package com.biblio.bibliotheque.repository.pret;

import com.biblio.bibliotheque.model.pret.Prolongement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProlongementRepository extends JpaRepository<Prolongement, Integer> {
    // Méthodes personnalisées si besoin
}
