package com.biblio.bibliotheque.repository.gestion;

import com.biblio.bibliotheque.model.gestion.Statut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatutRepository extends JpaRepository<Statut, Integer> {
    // Aucun ajout de méthode personnalisée pour l’instant
}
