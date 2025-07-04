package com.biblio.bibliotheque.repository.livre;

import com.biblio.bibliotheque.model.livre.EtatExemplaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EtatExemplaireRepository extends JpaRepository<EtatExemplaire, Integer> {

    @Query("""
        SELECT ee FROM EtatExemplaire ee
        WHERE ee.exemplaire.id_exemplaire = :id
        ORDER BY ee.date_modif DESC
        LIMIT 1
    """)
    EtatExemplaire findLatestEtatForExemplaire(@Param("id") Integer idExemplaire);

}
