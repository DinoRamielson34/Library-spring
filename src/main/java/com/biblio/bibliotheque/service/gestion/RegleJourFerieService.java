package com.biblio.bibliotheque.service.gestion;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblio.bibliotheque.model.gestion.RegleJourFerie;
import com.biblio.bibliotheque.repository.gestion.RegleJourFerieRepository;

@Service
public class RegleJourFerieService {

    @Autowired
    private RegleJourFerieRepository regleJourFerieRepository;

    public List<RegleJourFerie> getAll() {
        return regleJourFerieRepository.findAll();
    }

    public Optional<RegleJourFerie> getById(Integer id) {
        return regleJourFerieRepository.findById(id);
    }

    public RegleJourFerie save(RegleJourFerie regleJourFerie) {
        return regleJourFerieRepository.save(regleJourFerie);
    }

    public void delete(Integer id) {
        regleJourFerieRepository.deleteById(id);
    }

    public int getLastComportement() {
        return regleJourFerieRepository.getLastComportement();
    }
    
}
