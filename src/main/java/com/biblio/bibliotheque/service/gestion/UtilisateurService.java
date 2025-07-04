package com.biblio.bibliotheque.service.gestion;

import com.biblio.bibliotheque.model.gestion.Utilisateur;
import com.biblio.bibliotheque.repository.gestion.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public List<Utilisateur> getAll() {
        return utilisateurRepository.findAll();
    }

    public Optional<Utilisateur> getById(Integer id) {
        return utilisateurRepository.findById(id);
    }

    public Utilisateur save(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public void delete(Integer id) {
        utilisateurRepository.deleteById(id);
    }

    public Optional<Utilisateur> findByUsernameAndMdp(String username, String mdp) {
        return utilisateurRepository.findByUsernameAndMdp(username, mdp);
    }
    public Optional<Utilisateur> login(String username, String mdp) {
        return utilisateurRepository.findByUsernameAndMdp(username, mdp);
    }
}
