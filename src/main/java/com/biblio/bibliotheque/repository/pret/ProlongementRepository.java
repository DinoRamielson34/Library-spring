package com.biblio.bibliotheque.repository.pret;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.biblio.bibliotheque.model.pret.Prolongement;

@Repository
public interface ProlongementRepository extends JpaRepository<Prolongement, Integer> {

    /**
     * Vérifie si un adhérent a des prêts prolongés encore actifs
     * Un prolongement est considéré comme actif si :
     * - Le statut est 1 (approuvé)
     * - La nouvelle date de fin est supérieure à la date actuelle
     */
    @Query("SELECT COUNT(p) FROM Prolongement p " +
           "WHERE p.pret.adherent.idAdherent = :idAdherent " +
           "AND p.statut = 1 " +
           "AND p.nouveauDateFinPret > :dateActuelle")
    int countProlongementsActifsParAdherent(@Param("idAdherent") Integer idAdherent, 
                                           @Param("dateActuelle") LocalDateTime dateActuelle);

    /**
     * Récupère tous les prolongements actifs d'un adhérent
     */
    @Query("SELECT p FROM Prolongement p " +
           "WHERE p.pret.adherent.idAdherent = :idAdherent " +
           "AND p.statut = 1 " +
           "AND p.nouveauDateFinPret > :dateActuelle")
    List<Prolongement> findProlongementsActifsParAdherent(@Param("idAdherent") Integer idAdherent, 
                                                         @Param("dateActuelle") LocalDateTime dateActuelle);

    /**
     * Vérifie si un prêt spécifique a un prolongement actif
     */
    @Query("SELECT COUNT(p) FROM Prolongement p " +
           "WHERE p.pret.id_pret = :idPret " +
           "AND p.statut = 1 " +
           "AND p.nouveauDateFinPret > :dateActuelle")
    int countProlongementsActifsParPret(@Param("idPret") Integer idPret, 
                                       @Param("dateActuelle") LocalDateTime dateActuelle);
}