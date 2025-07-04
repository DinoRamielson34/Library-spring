package com.biblio.bibliotheque.repository.livre;

import com.biblio.bibliotheque.model.livre.Etat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtatRepository extends JpaRepository<Etat, Integer> {
    /*
    // Recherche un état par nom
    Etat findByNom(String nom);

    // Vérifie si un état existe par nom
    boolean existsByNom(String nom);
    */
}
