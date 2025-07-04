package com.biblio.bibliotheque.repository.pret;

import com.biblio.bibliotheque.model.pret.TypeExemplairePret;
import com.biblio.bibliotheque.model.pret.TypeExemplairePretId;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeExemplairePretRepository extends JpaRepository<TypeExemplairePret, TypeExemplairePretId> {
    // Méthodes spécifiques si besoin
}
