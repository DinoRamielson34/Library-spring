package com.biblio.bibliotheque.repository.gestion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblio.bibliotheque.model.gestion.Adherent;
import com.biblio.bibliotheque.model.gestion.Utilisateur;
import java.util.Optional;

@Repository
public interface AdherentRepository extends JpaRepository<Adherent, Integer> {
    Optional<Adherent> findByUtilisateur(Utilisateur utilisateur);
}
