package com.biblio.bibliotheque.service.gestion;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblio.bibliotheque.model.gestion.Abonnement;
import com.biblio.bibliotheque.repository.gestion.AbonnementRepository;

@Service
public class AbonnementService {

    @Autowired
    private AbonnementRepository abonnementRepository;

    public List<Abonnement> getAll() {
        return abonnementRepository.findAll();
    }

    public Optional<Abonnement> getById(Integer id) {
        return abonnementRepository.findById(id);
    }

    public Abonnement save(Abonnement abonnement) {
        return abonnementRepository.save(abonnement);
    }

    public void delete(Integer id) {
        abonnementRepository.deleteById(id);
    }

}
