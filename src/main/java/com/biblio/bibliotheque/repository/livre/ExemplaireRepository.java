package com.biblio.bibliotheque.repository.livre;

import com.biblio.bibliotheque.model.livre.Exemplaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExemplaireRepository extends JpaRepository<Exemplaire, Integer> {

    @Query("SELECT e.livre.idLivre FROM Exemplaire e WHERE e.id_exemplaire = :idExemplaire")
    Integer getIdLivreByIdExemplaire(@Param("idExemplaire") Integer idExemplaire);

}
