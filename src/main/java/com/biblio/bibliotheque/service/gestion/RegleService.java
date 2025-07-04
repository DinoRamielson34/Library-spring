package com.biblio.bibliotheque.service.gestion;

import com.biblio.bibliotheque.model.gestion.Regle;
import com.biblio.bibliotheque.repository.gestion.RegleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegleService {

    @Autowired
    private RegleRepository regleRepository;

    public Optional<Regle> getById(Integer id) {
        return regleRepository.findById(id);
    }
}
