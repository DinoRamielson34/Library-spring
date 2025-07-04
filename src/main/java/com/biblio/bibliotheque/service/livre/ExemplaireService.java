package com.biblio.bibliotheque.service.livre;

import com.biblio.bibliotheque.model.livre.EtatExemplaire;
import com.biblio.bibliotheque.model.livre.Exemplaire;
import com.biblio.bibliotheque.repository.livre.EtatExemplaireRepository;
import com.biblio.bibliotheque.repository.livre.ExemplaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExemplaireService {

    private final ExemplaireRepository exemplaireRepository;

    @Autowired
    public ExemplaireService(ExemplaireRepository exemplaireRepository) {
        this.exemplaireRepository = exemplaireRepository;
    }
    @Autowired
    private EtatExemplaireRepository etatExemplaireRepository;

    public boolean isExemplaireDisponible(Integer idExemplaire) {
    EtatExemplaire latestEtat = etatExemplaireRepository.findLatestEtatForExemplaire(idExemplaire);

    if (latestEtat == null || latestEtat.getEtat() == null) {
        return false; 
    }

    String nomEtat = latestEtat.getEtat().getNom().toLowerCase();

    return nomEtat.equals("disponible");
    }

    // Récupérer tous les exemplaires
    public List<Exemplaire> getAllExemplaires() {
        return exemplaireRepository.findAll();
    }

    // Récupérer un exemplaire par ID
    public Optional<Exemplaire> getExemplaireById(Integer id) {
        return exemplaireRepository.findById(id);
    }

    // Ajouter ou modifier un exemplaire
    public Exemplaire saveExemplaire(Exemplaire exemplaire) {
        return exemplaireRepository.save(exemplaire);
    }

    // Supprimer un exemplaire
    public void deleteExemplaire(Integer id) {
        exemplaireRepository.deleteById(id);
    }

    


    // (Optionnel) Rechercher un exemplaire par code
    // public Exemplaire getExemplaireByCode(String code) {
    //     return exemplaireRepository.findByCode(code);
    // }

    // (Optionnel) Rechercher les exemplaires par ID de livre
    // public List<Exemplaire> getExemplairesByLivreId(Integer idLivre) {
    //     return exemplaireRepository.findByLivreId(idLivre);
    // }
}
