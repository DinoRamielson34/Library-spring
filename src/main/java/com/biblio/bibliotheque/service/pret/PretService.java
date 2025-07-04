package com.biblio.bibliotheque.service.pret;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import com.biblio.bibliotheque.model.pret.Pret;
import com.biblio.bibliotheque.repository.pret.PretRepository;

@Service
public class PretService {

    @Autowired
    private PretRepository pretRepository;

    public List<Pret> getAllPrets() {
        return pretRepository.findAll();
    }

    public Pret getPretById(Integer id) {
        return pretRepository.findById(id).orElse(null);
    }    

    public Pret savePret(Pret pret) {
        return pretRepository.save(pret);
    }

    public void deletePret(Integer id) {
        pretRepository.deleteById(id);
    }
    public int countPretsActifsParAdherentALaDate(Integer idAdherent, LocalDate date) {
        return pretRepository.countPretsActifsParAdherentALaDate(idAdherent, date);
    }

    public LocalDate ajusterDateFin(LocalDate dateDebut, int nbJours) {
        if (dateDebut == null || nbJours < 0) {
            throw new IllegalArgumentException("Date de début ou nombre de jours invalide");
        }
        return dateDebut.plusDays(nbJours);
    }


    public boolean existById(Integer idPret){
        return pretRepository.existsById(idPret);
    }

    public boolean isPretPourAdherent(Integer idPret, Integer idAdherent) {
        return pretRepository.pretAppartientAdherent(idPret, idAdherent);
    }

    public LocalDate getDateFinPret(Integer idPret) {
    return pretRepository.getDateFinById(idPret);
    }

}
