package com.biblio.bibliotheque.service.sanction;

import com.biblio.bibliotheque.model.sanction.PenaliteProfil;
import com.biblio.bibliotheque.repository.sanction.PenaliteProfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PenaliteProfilService {

    private final PenaliteProfilRepository penaliteProfilRepository;

    @Autowired
    public PenaliteProfilService(PenaliteProfilRepository penaliteProfilRepository) {
        this.penaliteProfilRepository = penaliteProfilRepository;
    }

    // Obtenir toutes les pénalités
    public List<PenaliteProfil> getAllPenalites() {
        return penaliteProfilRepository.findAll();
    }

    // Obtenir une pénalité par ID
    public Optional<PenaliteProfil> getPenaliteById(Integer id) {
        return penaliteProfilRepository.findById(id);
    }

    // Ajouter ou modifier une pénalité
    public PenaliteProfil savePenalite(PenaliteProfil penaliteProfil) {
        return penaliteProfilRepository.save(penaliteProfil);
    }

    // Supprimer une pénalité
    public void deletePenalite(Integer id) {
        penaliteProfilRepository.deleteById(id);
    }


}
