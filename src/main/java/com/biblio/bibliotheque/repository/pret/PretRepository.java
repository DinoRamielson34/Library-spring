package com.biblio.bibliotheque.repository.pret;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biblio.bibliotheque.model.gestion.Adherent;
import com.biblio.bibliotheque.model.pret.Pret;

public interface PretRepository extends JpaRepository<Pret, Integer> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire
    List<Pret> findByAdherent(Adherent adherent);

    @Query("SELECT COUNT(p) FROM Pret p WHERE p.adherent.idAdherent = :idAdherent AND p.date_debut <= :nouvelleDate AND (p.date_fin IS NULL OR p.date_fin > :nouvelleDate)AND p.type.nom <> 'Sur place'")
    int countPretsActifsParAdherentALaDate(@Param("idAdherent") Integer idAdherent,
            @Param("nouvelleDate") LocalDate nouvelleDate);

    @Override
    boolean existsById(Integer id_pret);

    @Query("SELECT COUNT(p) > 0 FROM Pret p WHERE p.id_pret = :idPret AND p.adherent.idAdherent = :idAdherent")
    boolean pretAppartientAdherent(@Param("idPret") Integer idPret, @Param("idAdherent") Integer idAdherent);

    @Query("SELECT p.date_fin FROM Pret p WHERE p.id_pret = :idPret")
    LocalDate getDateFinById(@Param("idPret") Integer idPret);

    @Query(value = "SELECT id_exemplaire FROM pret WHERE id_pret = :idPret", nativeQuery = true)
    Integer getIdExemplaireByPret(@Param("idPret") Integer idPret);

}
