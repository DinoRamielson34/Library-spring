package com.biblio.bibliotheque.service.gestion;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblio.bibliotheque.model.gestion.AbonnementAdherent;
import com.biblio.bibliotheque.repository.gestion.AbonnementAdherentRepository;

@Service
public class AbonnementAdherentService {

    @Autowired
    private AbonnementAdherentRepository abonnementAdherentRepository;

    public List<AbonnementAdherent> getAll() {
        return abonnementAdherentRepository.findAll();
    }

    public boolean adherentAAbonnementActif(Integer idAdherent, LocalDate date) {
        return abonnementAdherentRepository.hasAbonnementActifAtDate(idAdherent, date);
    }


}

