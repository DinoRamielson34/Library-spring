package com.biblio.bibliotheque.service.livre;

import com.biblio.bibliotheque.model.livre.EtatExemplaire;
import com.biblio.bibliotheque.repository.livre.EtatExemplaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtatExemplaireService {

    private final EtatExemplaireRepository etatExemplaireRepository;

    @Autowired
    public EtatExemplaireService(EtatExemplaireRepository etatExemplaireRepository) {
        this.etatExemplaireRepository = etatExemplaireRepository;
    }

    // Récupérer tous les états d'exemplaire
    public List<EtatExemplaire> getAllEtatExemplaires() {
        return etatExemplaireRepository.findAll();
    }

    // Récupérer un état par ID
    public Optional<EtatExemplaire> getEtatExemplaireById(Integer id) {
        return etatExemplaireRepository.findById(id);
    }

    // Ajouter ou modifier un état d'exemplaire
    public EtatExemplaire saveEtatExemplaire(EtatExemplaire etatExemplaire) {
        return etatExemplaireRepository.save(etatExemplaire);
    }

    // Supprimer un état d'exemplaire
    public void deleteEtatExemplaire(Integer id) {
        etatExemplaireRepository.deleteById(id);
    }

    // (Optionnel) Récupérer les états par ID d'exemplaire
    // public List<EtatExemplaire> getEtatByExemplaireId(Integer idExemplaire) {
    //     return etatExemplaireRepository.findByExemplaireId(idExemplaire);
    // }
}
