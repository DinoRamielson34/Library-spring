package com.biblio.bibliotheque.repository.livre;

import com.biblio.bibliotheque.model.livre.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Integer> {

    // Recherche par nom
    //Type findByNom(String nom);

    // Vérifie l'existence par nom
    //boolean existsByNom(String nom);
}
