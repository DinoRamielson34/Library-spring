package com.biblio.bibliotheque.service.livre;

import com.biblio.bibliotheque.model.livre.Livre;
import com.biblio.bibliotheque.repository.livre.LivreRepository;
import com.biblio.bibliotheque.repository.livre.ExemplaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivreService {

    private final LivreRepository livreRepository;
    private final ExemplaireRepository exemplaireRepository;

    @Autowired
    public LivreService(LivreRepository livreRepository, ExemplaireRepository exemplaireRepository) {
        this.livreRepository = livreRepository;
        this.exemplaireRepository = exemplaireRepository;
    }

    public List<Livre> getAllLivres() {
        return livreRepository.findAll();
    }

    public Optional<Livre> getLivreById(Integer id) {
        return livreRepository.findById(id);
    }

    public Livre saveLivre(Livre livre) {
        return livreRepository.save(livre);
    }

    public void deleteLivre(Integer id) {
        livreRepository.deleteById(id);
    }

    public Integer getAgeRestrictionByIdLivre(Integer idLivre) {
        return livreRepository.getAgeRestrictionByIdLivre(idLivre);
    }

    public Integer getIdLivreByIdExemplaire(Integer idExemplaire) {
        return exemplaireRepository.getIdLivreByIdExemplaire(idExemplaire);
    }
}