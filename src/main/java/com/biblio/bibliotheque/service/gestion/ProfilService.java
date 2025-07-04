package com.biblio.bibliotheque.service.gestion;

import com.biblio.bibliotheque.model.gestion.Profil;
import com.biblio.bibliotheque.repository.gestion.ProfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfilService {

    @Autowired
    private ProfilRepository profilRepository;

    public List<Profil> getAll() {
        return profilRepository.findAll();
    }

    public Optional<Profil> getById(Integer id) {
        return profilRepository.findById(id);
    }

    public Profil save(Profil profil) {
        return profilRepository.save(profil);
    }

    public void delete(Integer id) {
        profilRepository.deleteById(id);
    }

    // Nouvelle méthode pour récupérer idRegle via idProfil
    public Integer getIdRegleByIdProfil(Integer idProfil) {
        return profilRepository.getIdRegleByIdProfil(idProfil);
    }

    // Exemples méthodes personnalisées
    /*
    public Profil findByNom(String nom) {
        return profilRepository.findByNom(nom);
    }

    public boolean existsByNom(String nom) {
        return profilRepository.existsByNom(nom);
    }
    */
}
