package com.biblio.bibliotheque.service.livre;

import com.biblio.bibliotheque.model.livre.Type;
import com.biblio.bibliotheque.repository.livre.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeService {

    private final TypeRepository typeRepository;

    @Autowired
    public TypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    // Récupérer tous les types
    public List<Type> getAllTypes() {
        return typeRepository.findAll();
    }

    // Récupérer un type par ID
    public Optional<Type> getTypeById(Integer id) {
        return typeRepository.findById(id);
    }

    // Ajouter ou modifier un type
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    // Supprimer un type
    public void deleteType(Integer id) {
        typeRepository.deleteById(id);
    }

    // (Optionnel) Rechercher un type par nom
    // public Type getTypeByNom(String nom) {
    //     return typeRepository.findByNom(nom);
    // }

    // (Optionnel) Vérifier si un type existe par nom
    // public boolean existsTypeByNom(String nom) {
    //     return typeRepository.existsByNom(nom);
    // }
}
