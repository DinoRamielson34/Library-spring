package com.biblio.bibliotheque.service.pret;

import com.biblio.bibliotheque.model.pret.TypeExemplairePret;
import com.biblio.bibliotheque.model.pret.TypeExemplairePretId;
import com.biblio.bibliotheque.repository.pret.TypeExemplairePretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeExemplairePretService {

    @Autowired
    private TypeExemplairePretRepository typeExemplairePretRepository;

    public List<TypeExemplairePret> getAll() {
        return typeExemplairePretRepository.findAll();
    }

    public Optional<TypeExemplairePret> getById(TypeExemplairePretId id) {
        return typeExemplairePretRepository.findById(id);
    }

    public TypeExemplairePret save(TypeExemplairePret entity) {
        return typeExemplairePretRepository.save(entity);
    }

    public void delete(TypeExemplairePretId id) {
        typeExemplairePretRepository.deleteById(id);
    }
}
