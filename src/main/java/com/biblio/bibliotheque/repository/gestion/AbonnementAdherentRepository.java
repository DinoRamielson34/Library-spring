package com.biblio.bibliotheque.repository.gestion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.biblio.bibliotheque.model.gestion.AbonnementAdherent;
import com.biblio.bibliotheque.model.gestion.AbonnementAdherentId;

@Repository
public interface AbonnementAdherentRepository extends JpaRepository<AbonnementAdherent, AbonnementAdherentId> {

    @Query(value = """
            SELECT COUNT(*) > 0
            FROM abonnement_adherent aa
            JOIN abonnement a ON aa.id_abonnement = a.id_abonnement
            WHERE aa.id_adherent = :idAdherent
              AND :dateToCheck BETWEEN aa.date_de_payement::date
                                  AND (aa.date_de_payement::date + (a.mois || ' months')::interval)::date
            """, nativeQuery = true)
    boolean hasAbonnementActifAtDate(@Param("idAdherent") Integer idAdherent,
            @Param("dateToCheck") java.time.LocalDate dateToCheck);

}
