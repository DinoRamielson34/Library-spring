package com.biblio.bibliotheque.repository.sanction;

import com.biblio.bibliotheque.model.sanction.Sanction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;

public interface SanctionRepository extends JpaRepository<Sanction, Integer> {
    @Query("SELECT COUNT(s) > 0 " +
           "FROM Sanction s " +
           "WHERE s.adherent.idAdherent = :idAdherent " +
           "AND :checkDate BETWEEN s.date_debut AND s.date_fin")
    boolean isAdherentSanctioned(@Param("idAdherent") Integer idAdherent, 
                                @Param("checkDate") LocalDateTime checkDate);
}