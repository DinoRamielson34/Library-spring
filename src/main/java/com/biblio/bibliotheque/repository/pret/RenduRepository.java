package com.biblio.bibliotheque.repository.pret;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblio.bibliotheque.model.pret.Rendu;

public interface RenduRepository extends JpaRepository<Rendu, Integer> {
    // Requête personnalisée si nécessaire
    
}
