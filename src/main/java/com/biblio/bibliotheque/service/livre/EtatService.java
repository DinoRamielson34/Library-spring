package com.biblio.bibliotheque.service.livre;

import com.biblio.bibliotheque.model.livre.Etat;
import com.biblio.bibliotheque.repository.livre.EtatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtatService {

    private final EtatRepository etatRepository;

    @Autowired
    public EtatService(EtatRepository etatRepository) {
        this.etatRepository = etatRepository;
    }

    // Récupérer tous les états
    public List<Etat> getAllEtats() {
        return etatRepository.findAll();
    }

    // Récupérer un état par ID
    public Optional<Etat> getEtatById(Integer id) {
        return etatRepository.findById(id);
    }

    // Ajouter ou modifier un état
    public Etat saveEtat(Etat etat) {
        return etatRepository.save(etat);
    }

    // Supprimer un état
    public void deleteEtat(Integer id) {
        etatRepository.deleteById(id);
    }

    // (Optionnel) Récupérer un état par nom
    // public Etat getEtatByNom(String nom) {
    //     return etatRepository.findByNom(nom);
    // }

    // (Optionnel) Vérifier si un état existe par nom
    // public boolean existsEtatByNom(String nom) {
    //     return etatRepository.existsByNom(nom);
    // }
}
