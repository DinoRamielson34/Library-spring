package com.biblio.bibliotheque.repository.gestion;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.biblio.bibliotheque.model.gestion.Adherent;
import com.biblio.bibliotheque.model.gestion.Utilisateur;

@Repository
public interface AdherentRepository extends JpaRepository<Adherent, Integer> {
    Optional<Adherent> findByUtilisateur(Utilisateur utilisateur);

    // ✅ Récupère uniquement la date de naissance
    @Query("SELECT a.dateDeNaissance FROM Adherent a WHERE a.idAdherent = :idAdherent")
    Optional<LocalDate> findDateDeNaissanceById(Integer idAdherent);
}
