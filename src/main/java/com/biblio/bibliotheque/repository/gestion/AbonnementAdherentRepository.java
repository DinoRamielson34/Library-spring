package com.biblio.bibliotheque.repository.gestion;

import com.biblio.bibliotheque.model.gestion.AbonnementAdherent;
import com.biblio.bibliotheque.model.gestion.AbonnementAdherentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbonnementAdherentRepository extends JpaRepository<AbonnementAdherent, AbonnementAdherentId> {

    // Exemples de méthodes personnalisées :
    //boolean existsByAbonnementIdAbonnementAndAdherentIdAdherent(Integer idAbonnement, Integer idAdherent);

}
