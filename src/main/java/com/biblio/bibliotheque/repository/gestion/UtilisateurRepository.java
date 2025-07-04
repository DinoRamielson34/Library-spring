package com.biblio.bibliotheque.repository.gestion;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblio.bibliotheque.model.gestion.Utilisateur;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    Optional<Utilisateur> findByUsernameAndMdp(String username, String mdp);
}
