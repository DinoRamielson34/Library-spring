package com.biblio.bibliotheque.repository.livre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.biblio.bibliotheque.model.livre.EtatExemplaire;

@Repository
public interface EtatExemplaireRepository extends JpaRepository<EtatExemplaire, Integer> {

    @Query(value = """
                SELECT * FROM etat_exemplaire
                WHERE id_exemplaire = :id
                ORDER BY date_modif DESC
                LIMIT 1
            """, nativeQuery = true)
    EtatExemplaire findLatestEtatForExemplaire(@Param("id") Integer idExemplaire);

}
