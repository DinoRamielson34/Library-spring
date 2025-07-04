package com.biblio.bibliotheque.repository.pret;

import com.biblio.bibliotheque.model.pret.Pret;
import com.biblio.bibliotheque.model.pret.StatusProlongement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusProlongementRepository extends JpaRepository<StatusProlongement, Integer> {
    boolean existsByPretAndStatusTraintement(Pret pret, int statusTraintement);
    List<StatusProlongement> findByStatusTraintement(int statusTraintement);
    long countByPretAndStatus(Pret pret, int status);
}