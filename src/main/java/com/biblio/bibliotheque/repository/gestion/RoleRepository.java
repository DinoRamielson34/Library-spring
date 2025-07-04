package com.biblio.bibliotheque.repository.gestion;

import com.biblio.bibliotheque.model.gestion.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    // Recherche un rôle par nom
    Role findByNom(String nom);

    // Vérifie si un rôle existe par nom
    boolean existsByNom(String nom);
}
