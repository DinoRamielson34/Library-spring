package com.biblio.bibliotheque.service.gestion;

import com.biblio.bibliotheque.model.gestion.Statut;
import com.biblio.bibliotheque.repository.gestion.StatutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatutService {

    @Autowired
    private StatutRepository statutRepository;

    public List<Statut> getAll() {
        return statutRepository.findAll();
    }

    public Optional<Statut> getById(Integer id) {
        return statutRepository.findById(id);
    }

    public Statut save(Statut statut) {
        return statutRepository.save(statut);
    }

    public void delete(Integer id) {
        statutRepository.deleteById(id);
    }

    // Méthodes personnalisées (à décommenter si implémentées dans repository)
    /*
    public Statut findByNom(String nom) {
        return statutRepository.findByNom(nom);
    }

    public boolean existsByNom(String nom) {
        return statutRepository.existsByNom(nom);
    }
    */
}
