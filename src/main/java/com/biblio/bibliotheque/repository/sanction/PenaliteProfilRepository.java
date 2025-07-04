package com.biblio.bibliotheque.repository.sanction;

import com.biblio.bibliotheque.model.sanction.PenaliteProfil;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PenaliteProfilRepository extends JpaRepository<PenaliteProfil, Integer> {
    // Par exemple :
    // List<PenaliteProfil> findByProfilId(Integer idProfil);
    
    @Query("SELECT p FROM PenaliteProfil p WHERE p.profil.id_profil = :idProfil ORDER BY p.date_modif DESC")
    Optional<PenaliteProfil> findTopByProfilIdOrderByDateModifDesc(@Param("idProfil") int idProfil);
}
