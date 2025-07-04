package com.biblio.bibliotheque.repository.gestion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.biblio.bibliotheque.model.gestion.RegleJourFerie;

@Repository
public interface RegleJourFerieRepository extends JpaRepository<RegleJourFerie, Integer> {

    @Query("SELECT r.comportement FROM RegleJourFerie r ORDER BY r.dateModif DESC LIMIT 1")
    int getLastComportement();
}
