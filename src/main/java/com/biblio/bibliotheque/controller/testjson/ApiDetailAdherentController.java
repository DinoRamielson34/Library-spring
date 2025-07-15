package com.biblio.bibliotheque.controller.testjson;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblio.bibliotheque.model.gestion.Adherent;
import com.biblio.bibliotheque.model.gestion.Regle;
import com.biblio.bibliotheque.model.pret.Pret;
import com.biblio.bibliotheque.service.gestion.AdherentService;
import com.biblio.bibliotheque.service.gestion.ProfilService;
import com.biblio.bibliotheque.service.gestion.RegleService;
import com.biblio.bibliotheque.service.pret.PretService;
import com.biblio.bibliotheque.service.sanction.SanctionService;

@RestController
@RequestMapping("/api/adherent")
public class ApiDetailAdherentController {

    @Autowired
    private AdherentService adherentService;

    @Autowired
    private PretService pretService;

    @Autowired
    private SanctionService sanctionService;

    @Autowired
    private ProfilService profilService;

    @Autowired
    private RegleService regleService;

    @GetMapping({ "/detailAdherent", "/detailAdherent/{id}" })
    public ResponseEntity<?> getAdherentDetails(@PathVariable(required = false) Integer id) {
        if (id == null) {
            // Return all adherents
            List<Map<String, Object>> allAdherents = adherentService.getAll().stream()
                    .map(this::buildAdherentResponse)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(allAdherents);
        }

        // Return single adherent by ID
        Optional<Adherent> adherentOpt = adherentService.getById(id);
        if (adherentOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(buildAdherentResponse(adherentOpt.get()));
    }

    private Map<String, Object> buildAdherentResponse(Adherent adherent) {
        Map<String, Object> response = new HashMap<>();
        response.put("idAdherent", adherent.getIdAdherent());
        response.put("nom", adherent.getNom());
        response.put("prenom", adherent.getPrenom());
        response.put("dateDeNaissance", adherent.getDateDeNaissance().toString());
        response.put("age", adherentService.getAge(adherent.getIdAdherent()));
        response.put("estActif", adherentService.getStatutAdherentOnDate(adherent.getIdAdherent(), LocalDate.now()));
        response.put("estSanctionne",
                sanctionService.isAdherentSanctioned(adherent.getIdAdherent(), LocalDateTime.now()));
        response.put("pretsActifs", getPretsActifs(adherent));

        // Additional useful information
        response.put("profil", adherent.getProfil() != null ? adherent.getProfil().getNom() : null);
        response.put("nombrePretsActifs",
                pretService.countPretsActifsParAdherentALaDate(adherent.getIdAdherent(), LocalDate.now()));

        Integer quotaLivres = null;
        if (adherent.getProfil() != null) {
            Integer idProfil = adherent.getProfil().getId_profil();
            Integer idRegle = profilService.getIdRegleByIdProfil(idProfil);
            Regle regle = (idRegle != null) ? regleService.getById(idRegle).orElse(null) : null;
            quotaLivres = (regle != null) ? regle.getNb_livre_preter_max() : null;
        }
        Integer resteAPreter = quotaLivres - pretService.countPretsActifsParAdherentALaDate(adherent.getIdAdherent(), LocalDate.now());

        response.put("quotaLivres", quotaLivres);
        response.put("Reste a preter", resteAPreter);

        return response;
    }

    private List<Map<String, Object>> getPretsActifs(Adherent adherent) {
        return pretService.getAllPrets().stream()
                .filter(pret -> pret.getAdherent().getIdAdherent().equals(adherent.getIdAdherent())
                        && pret.getDate_debut().isBefore(LocalDate.now())
                        && (pret.getDate_fin() == null || pret.getDate_fin().isAfter(LocalDate.now()))
                        && !pret.getType().getNom().equals("Sur place"))
                .map(this::buildPretResponse)
                .collect(Collectors.toList());
    }

    private Map<String, Object> buildPretResponse(Pret pret) {
        Map<String, Object> pretResponse = new HashMap<>();
        pretResponse.put("idPret", pret.getId_pret());
        pretResponse.put("dateDebut", pret.getDate_debut().toString());
        pretResponse.put("dateFin", pret.getDate_fin() != null ? pret.getDate_fin().toString() : null);
        pretResponse.put("exemplaireId", pret.getExemplaire() != null ? pret.getExemplaire().getId_exemplaire() : null);
        pretResponse.put("typePret", pret.getType() != null ? pret.getType().getNom() : null);
        return pretResponse;
    }

}