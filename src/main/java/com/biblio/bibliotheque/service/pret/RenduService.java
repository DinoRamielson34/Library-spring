package com.biblio.bibliotheque.service.pret;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblio.bibliotheque.model.livre.Etat;
import com.biblio.bibliotheque.model.livre.EtatExemplaire;
import com.biblio.bibliotheque.model.livre.Exemplaire;
import com.biblio.bibliotheque.model.pret.Pret;
import com.biblio.bibliotheque.model.pret.Rendu;
import com.biblio.bibliotheque.repository.pret.RenduRepository;
import com.biblio.bibliotheque.service.gestion.AdherentService;
import com.biblio.bibliotheque.service.gestion.JourFerieService;
import com.biblio.bibliotheque.service.gestion.RegleJourFerieService;
import com.biblio.bibliotheque.service.livre.EtatExemplaireService;
import com.biblio.bibliotheque.service.livre.EtatService;
import com.biblio.bibliotheque.service.livre.ExemplaireService;
import com.biblio.bibliotheque.service.sanction.SanctionService;

@Service
public class RenduService {

    @Autowired
    private RenduRepository renduRepository;

    @Autowired
    private PretService pretService;

    @Autowired
    private AdherentService adherentService;

    @Autowired
    private JourFerieService jourFerieService;

    @Autowired
    private RegleJourFerieService regleJourFerieService;

    @Autowired
    private EtatService etatService;

    @Autowired
    private ExemplaireService exemplaireService;

    @Autowired
    private EtatExemplaireService etatExemplaireService;

    @Autowired
    private SanctionService sanctionService;

    public List<Rendu> getAllRendus() {
        return renduRepository.findAll();
    }

    public Optional<Rendu> getRenduById(Integer id) {
        return renduRepository.findById(id);
    }

    public void saveRendu(Rendu rendu) {
        renduRepository.save(rendu);
    }

    public void deleteRendu(Integer id) {
        renduRepository.deleteById(id);
    }

    public boolean isExistPret(Integer idAdherent) {
        return renduRepository.existsById(idAdherent);
    }

    public String validerRendu(Integer idAdherent, Integer idPret, LocalDate dateRendu) {
    boolean existAdherent = adherentService.isExistAdherent(idAdherent);
    boolean existPret = pretService.existById(idPret);

    if (!existAdherent) {
        return "Erreur : l'adhérent avec l'ID " + idAdherent + " n'existe pas.";
    } else if (!existPret) {
        return "Erreur : le prêt avec l'ID " + idPret + " n'existe pas.";
    } else {
        boolean correspond = pretService.isPretPourAdherent(idPret, idAdherent);
        if (!correspond) {
            return "Erreur : ce prêt n'appartient pas à cet adhérent.";
        } else {
            LocalDate dateFinPret = pretService.getDateFinPret(idPret);
            int comportement = regleJourFerieService.getLastComportement();
            Pret p = pretService.getPretById(idPret);
            Rendu r = new Rendu(dateRendu, p);
            saveRendu(r);

            // Récupérer l'exemplaire du prêt pour mettre à jour son état
            Integer idExemplaire = pretService.getIdExemplaireByPret(idPret);
            Optional<Exemplaire> exemplaireOpt = exemplaireService.getExemplaireById(idExemplaire);
            
            if (exemplaireOpt.isPresent()) {
                Exemplaire exemplaire = exemplaireOpt.get();
                
                // Récupérer l'état "Disponible" (id = 1)
                Optional<Etat> etatDisponibleOpt = etatService.getEtatById(1);
                
                if (etatDisponibleOpt.isPresent()) {
                    Etat etatDisponible = etatDisponibleOpt.get();
                    
                    // Créer un nouvel état pour l'exemplaire
                    EtatExemplaire nouvelEtat = new EtatExemplaire();
                    nouvelEtat.setDate_modif(LocalDateTime.now()); // Date et heure actuelles
                    nouvelEtat.setEtat(etatDisponible); // Objet Etat (Disponible)
                    nouvelEtat.setExemplaire(exemplaire); // Objet Exemplaire
                    
                    // Sauvegarder le nouvel état
                    etatExemplaireService.saveEtatExemplaire(nouvelEtat);
                }
            }

            // Calcul date limite ajustée avec isWeekend et isJourFerie
            LocalDate dateLimite = dateFinPret;

            while (jourFerieService.isWeekend(dateLimite) || jourFerieService.isJourFerie(dateLimite)) {
                if (comportement == 1) {
                    dateLimite = dateLimite.plusDays(1); // Avance jusqu'au jour ouvrable
                } else {
                    dateLimite = dateLimite.minusDays(1); // Recule jusqu'au jour ouvrable
                }
            }

            boolean enRetard = dateRendu.isAfter(dateLimite);

            if (enRetard) {
                try {
                    sanctionService.sanctionnerAdherent(idAdherent, dateRendu, dateRendu.plusDays(7),
                            "Retard de rendu");
                    return "📛 Rendu en retard ! Une sanction a été appliquée. Exemplaire remis à disposition.";
                } catch (Exception e) {
                    return "📛 Rendu en retard ! Erreur lors de l'application de la sanction : " + e.getMessage();
                }
            } else {
                return "✅ Rendu dans les délais. Exemplaire remis à disposition !";
            }
        }
    }
}
}
