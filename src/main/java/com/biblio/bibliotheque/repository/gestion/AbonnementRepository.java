package com.biblio.bibliotheque.repository.gestion;

import com.biblio.bibliotheque.model.gestion.Abonnement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbonnementRepository extends JpaRepository<Abonnement, Integer> {

    // Tu peux ajouter des méthodes personnalisées ici si besoin, par exemple :
    //Abonnement findByMoisAndAnnee(Integer mois, Integer annee);

}
