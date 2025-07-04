package com.biblio.bibliotheque.repository.gestion;

import com.biblio.bibliotheque.model.gestion.Profil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilRepository extends JpaRepository<Profil, Integer> {

    @Query("SELECT p.regle.id_regle FROM Profil p WHERE p.id_profil = :idProfil")
    Integer getIdRegleByIdProfil(@Param("idProfil") Integer idProfil);

}
