package com.biblio.bibliotheque.service.gestion;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblio.bibliotheque.model.gestion.Adherent;
import com.biblio.bibliotheque.repository.gestion.AdherentRepository;
import com.biblio.bibliotheque.repository.gestion.StatutAdherentRepository;

@Service
public class AdherentService {

    @Autowired
    private AdherentRepository adherentRepository;

    @Autowired
    private StatutAdherentRepository statutAdherentRepository;

    public List<Adherent> getAll() {
        return adherentRepository.findAll();
    }

    public Optional<Adherent> getById(Integer id) {
        return adherentRepository.findById(id);
    }

    public Adherent save(Adherent adherent) {
        return adherentRepository.save(adherent);
    }

    public void delete(Integer id) {
        adherentRepository.deleteById(id);
    }

    public String getStatutAdherentOnDate(Integer idAdherent, LocalDate date) {
        boolean actif = statutAdherentRepository.findStatutActifByAdherentIdAndDate(idAdherent, date).isPresent();
        return actif ? "actif" : "inactif";
    }

    public int getAgeAtDate(Integer idAdherent, LocalDate date) {
        Optional<Adherent> optional = adherentRepository.findById(idAdherent);
        if (optional.isPresent()) {
            LocalDate naissance = optional.get().getDateDeNaissance();
            return Period.between(naissance, date).getYears();
        }
        return -1; // ou lève une exception
    }

    public boolean isExistAdherent(Integer idAdherent) {
        return adherentRepository.existsById(idAdherent);
    }

    public Integer getAge(Integer idAdherent) {
        Optional<LocalDate> optionalDateNaissance = adherentRepository.findDateDeNaissanceById(idAdherent);
        if (optionalDateNaissance.isPresent()) {
            LocalDate dateNaissance = optionalDateNaissance.get();
            return Period.between(dateNaissance, LocalDate.now()).getYears();
        } else {
            return null; // ou lever une exception personnalisée
        }
    }
}
