package com.biblio.bibliotheque.repository.livre;

import com.biblio.bibliotheque.model.livre.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Integer> {
    @Query("SELECT COALESCE(l.restriction.ageMin, 0) " +
           "FROM Livre l " +
           "WHERE l.idLivre = :idLivre")
    Integer getAgeRestrictionByIdLivre(@Param("idLivre") Integer idLivre);

    /*
    // Recherche par titre (exact ou partiel)
    List<Livre> findByTitreContainingIgnoreCase(String titre);

    // Recherche par auteur (exact ou partiel)
    List<Livre> findByAuteurContainingIgnoreCase(String auteur);
    */
}