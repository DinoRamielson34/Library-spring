package com.biblio.bibliotheque.service.pret;

import com.biblio.bibliotheque.model.pret.StatusProlongement;
import com.biblio.bibliotheque.repository.pret.StatusProlongementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusProlongementService {

    @Autowired
    private StatusProlongementRepository statusProlongementRepository;

    public List<StatusProlongement> getAll() {
        return statusProlongementRepository.findAll();
    }

    public Optional<StatusProlongement> getById(Integer id) {
        return statusProlongementRepository.findById(id);
    }

    public StatusProlongement save(StatusProlongement statusProlongement) {
        return statusProlongementRepository.save(statusProlongement);
    }

    public void delete(Integer id) {
        statusProlongementRepository.deleteById(id);
    }

}
