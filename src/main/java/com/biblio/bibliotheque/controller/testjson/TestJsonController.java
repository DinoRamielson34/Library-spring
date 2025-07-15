package com.biblio.bibliotheque.controller.testjson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblio.bibliotheque.model.livre.Exemplaire;
import com.biblio.bibliotheque.model.livre.Livre;
import com.biblio.bibliotheque.service.livre.ExemplaireService;
import com.biblio.bibliotheque.service.livre.LivreService;

@RestController
@RequestMapping("/api")
public class TestJsonController {

    @Autowired
    private LivreService livreService;

    @Autowired
    private ExemplaireService exemplaireService;

    @GetMapping("/detailLivre/{id}")
    public ResponseEntity<Map<String, Object>> getDetailLivre(@PathVariable Integer id) {
        Optional<Livre> livreOpt = livreService.getLivreById(id);
        
        if (!livreOpt.isPresent()) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Livre non trouvé");
            errorResponse.put("id", id);
            return ResponseEntity.notFound().build();
        }

        Livre livre = livreOpt.get();
        Map<String, Object> response = new HashMap<>();
        
        // Informations du livre
        response.put("id", livre.getIdLivre());
        response.put("titre", livre.getTitre());
        response.put("auteur", livre.getAuteur());
        response.put("restriction", livre.getRestriction());
        
        // Récupérer les exemplaires du livre
        List<Exemplaire> exemplaires = exemplaireService.getAllExemplaires()
            .stream()
            .filter(ex -> ex.getLivre() != null && ex.getLivre().getIdLivre().equals(id))
            .toList();
        
        List<Map<String, Object>> exemplairesDetails = new ArrayList<>();
        
        for (Exemplaire exemplaire : exemplaires) {
            Map<String, Object> exemplaireInfo = new HashMap<>();
            exemplaireInfo.put("id", exemplaire.getId_exemplaire());
            exemplaireInfo.put("code", exemplaire.getCode());
            exemplaireInfo.put("disponibilite", exemplaireService.getDisponibiliteExemplaire(exemplaire.getId_exemplaire()));
            exemplairesDetails.add(exemplaireInfo);
        }
        
        response.put("exemplaires", exemplairesDetails);
        response.put("nombreExemplaires", exemplairesDetails.size());
        
        // Compter les exemplaires disponibles
        long exemplairesDisponibles = exemplairesDetails.stream()
            .filter(ex -> "Disponible".equals(ex.get("disponibilite")))
            .count();
        
        response.put("exemplairesDisponibles", exemplairesDisponibles);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/detailLivre")
    public ResponseEntity<Map<String, Object>> getAllLivresDetails() {
        List<Livre> livres = livreService.getAllLivres();
        List<Map<String, Object>> livresDetails = new ArrayList<>();
        
        for (Livre livre : livres) {
            Map<String, Object> livreInfo = new HashMap<>();
            livreInfo.put("id", livre.getIdLivre());
            livreInfo.put("titre", livre.getTitre());
            livreInfo.put("auteur", livre.getAuteur());
            livreInfo.put("restriction", livre.getRestriction());
            
            // Récupérer les exemplaires du livre
            List<Exemplaire> exemplaires = exemplaireService.getAllExemplaires()
                .stream()
                .filter(ex -> ex.getLivre() != null && ex.getLivre().getIdLivre().equals(livre.getIdLivre()))
                .toList();
            
            List<Map<String, Object>> exemplairesDetails = new ArrayList<>();
            
            for (Exemplaire exemplaire : exemplaires) {
                Map<String, Object> exemplaireInfo = new HashMap<>();
                exemplaireInfo.put("id", exemplaire.getId_exemplaire());
                exemplaireInfo.put("code", exemplaire.getCode());
                exemplaireInfo.put("disponibilite", exemplaireService.getDisponibiliteExemplaire(exemplaire.getId_exemplaire()));
                exemplairesDetails.add(exemplaireInfo);
            }
            
            livreInfo.put("exemplaires", exemplairesDetails);
            livreInfo.put("nombreExemplaires", exemplairesDetails.size());
            
            // Compter les exemplaires disponibles
            long exemplairesDisponibles = exemplairesDetails.stream()
                .filter(ex -> "Disponible".equals(ex.get("disponibilite")))
                .count();
            
            livreInfo.put("exemplairesDisponibles", exemplairesDisponibles);
            
            livresDetails.add(livreInfo);
        }
        
        Map<String, Object> response = new HashMap<>();
        response.put("livres", livresDetails);
        response.put("totalLivres", livresDetails.size());
        
        return ResponseEntity.ok(response);
    }
}