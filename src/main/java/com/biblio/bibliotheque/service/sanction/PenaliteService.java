package com.biblio.bibliotheque.service.sanction;

import com.biblio.bibliotheque.model.sanction.Penalite;
import com.biblio.bibliotheque.repository.sanction.PenaliteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PenaliteService {

    private final PenaliteRepository penaliteRepository;

    @Autowired
    public PenaliteService(PenaliteRepository penaliteRepository) {
        this.penaliteRepository = penaliteRepository;
    }

    // Obtenir toutes les pénalités
    public List<Penalite> getAllPenalites() {
        return penaliteRepository.findAll();
    }

    // Obtenir une pénalité par ID
    public Optional<Penalite> getPenaliteById(Integer id) {
        return penaliteRepository.findById(id);
    }

    // Ajouter ou modifier une pénalité
    public Penalite savePenalite(Penalite penalite) {
        return penaliteRepository.save(penalite);
    }

    // Supprimer une pénalité par ID
    public void deletePenalite(Integer id) {
        penaliteRepository.deleteById(id);
    }
}
