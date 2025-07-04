package com.biblio.bibliotheque.service.gestion;

import com.biblio.bibliotheque.model.gestion.Role;
import com.biblio.bibliotheque.repository.gestion.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    public Optional<Role> getById(Integer id) {
        return roleRepository.findById(id);
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public void delete(Integer id) {
        roleRepository.deleteById(id);
    }

    // Méthodes personnalisées

    public Role findByNom(String nom) {
        return roleRepository.findByNom(nom);
    }

    public boolean existsByNom(String nom) {
        return roleRepository.existsByNom(nom);
    }
}
