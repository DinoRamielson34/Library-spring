package com.biblio.bibliotheque.repository.gestion;

import com.biblio.bibliotheque.model.gestion.Regle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegleRepository extends JpaRepository<Regle, Integer> {

    // Tu peux ajouter des recherches personnalisées si besoin
}
