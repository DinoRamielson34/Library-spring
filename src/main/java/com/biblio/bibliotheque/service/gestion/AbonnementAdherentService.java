package com.biblio.bibliotheque.service.gestion;

import com.biblio.bibliotheque.model.gestion.AbonnementAdherent;
import com.biblio.bibliotheque.model.gestion.AbonnementAdherentId;
import com.biblio.bibliotheque.repository.gestion.AbonnementAdherentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AbonnementAdherentService {

    @Autowired
    private AbonnementAdherentRepository abonnementAdherentRepository;

    public List<AbonnementAdherent> getAll() {
        return abonnementAdherentRepository.findAll();
    }

    public Optional<AbonnementAdherent> getById(AbonnementAdherentId id) {
        return abonnementAdherentRepository.findById(id);
    }

    public AbonnementAdherent save(AbonnementAdherent abonnementAdherent) {
        return abonnementAdherentRepository.save(abonnementAdherent);
    }

    public void delete(AbonnementAdherentId id) {
        abonnementAdherentRepository.deleteById(id);
    }

    // Exemple de méthode personnalisée (à décommenter et implémenter dans le repository si besoin)
    /*
    public boolean existsByAbonnementIdAbonnementAndAdherentIdAdherent(Integer idAbonnement, Integer idAdherent) {
        return abonnementAdherentRepository.existsByAbonnementIdAbonnementAndAdherentIdAdherent(idAbonnement, idAdherent);
    }
    */
}

