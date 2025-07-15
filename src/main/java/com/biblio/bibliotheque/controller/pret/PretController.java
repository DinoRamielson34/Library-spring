package com.biblio.bibliotheque.controller.pret;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biblio.bibliotheque.model.gestion.Adherent;
import com.biblio.bibliotheque.model.gestion.Regle;
import com.biblio.bibliotheque.model.gestion.Utilisateur;
import com.biblio.bibliotheque.model.livre.Etat;
import com.biblio.bibliotheque.model.livre.EtatExemplaire;
import com.biblio.bibliotheque.model.livre.Type;
import com.biblio.bibliotheque.model.pret.Pret;
import com.biblio.bibliotheque.repository.gestion.AdherentRepository;
import com.biblio.bibliotheque.repository.livre.ExemplaireRepository;
import com.biblio.bibliotheque.repository.livre.TypeRepository;
import com.biblio.bibliotheque.repository.pret.PretRepository;
import com.biblio.bibliotheque.service.gestion.AdherentService;
import com.biblio.bibliotheque.service.gestion.ProfilService;
import com.biblio.bibliotheque.service.gestion.RegleService;
import com.biblio.bibliotheque.service.livre.EtatExemplaireService;
import com.biblio.bibliotheque.service.livre.ExemplaireService;
import com.biblio.bibliotheque.service.livre.LivreService;
import com.biblio.bibliotheque.service.pret.PretService;
import com.biblio.bibliotheque.service.sanction.SanctionService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/preter")
public class PretController {

    @Autowired
    private PretRepository pretRepository;

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private AdherentService adherentService;

    @Autowired
    private ExemplaireService exemplaireService;

    @Autowired
    private PretService pretService;

    @Autowired
    private SanctionService sanctionService;

    @Autowired
    private LivreService livreService;

    @Autowired
    private ProfilService profilService;

    @Autowired
    private RegleService regleService;

    @Autowired
    private AdherentRepository adherentRepository;

    @Autowired
    private EtatExemplaireService etatExemplaireService;

    @GetMapping("/formpreter/livre")
    public String showFormPreterLivre(Model model) {
        model.addAttribute("pret", new Pret());
        model.addAttribute("exemplaires", exemplaireRepository.findAll());
        model.addAttribute("adherents", adherentService.getAll());
        model.addAttribute("types", typeRepository.findAll());
        return "views/preter/form_preter";
    }

    @PostMapping("/add")
    public String savePret(@ModelAttribute Pret pret, Model model) {
        Integer idAdherent = pret.getAdherent().getIdAdherent();
        LocalDate dateDebut = pret.getDate_debut();
        Integer idExemplaire = pret.getExemplaire().getId_exemplaire();

        Integer idType = 2;

        // 🔧 Crée un objet Type avec juste l'id
        Type type = new Type();
        type.setId_type(idType);

        // 🧩 Affecte ce type à l’objet Pret
        pret.setType(type);

        Integer idLivre = livreService.getIdLivreByIdExemplaire(idExemplaire);
        Integer ageRestriction = (idLivre != null) ? livreService.getAgeRestrictionByIdLivre(idLivre) : null;
        Integer ageAdherent = adherentService.getAgeAtDate(idAdherent, dateDebut);

        System.out.println("L'age de restriction: " + ageRestriction);
        System.out.println(ageAdherent);

        Optional<Adherent> optionalAdherent = adherentService.getById(idAdherent);
        if (optionalAdherent.isEmpty()) {
            model.addAttribute("message", "Adhérent introuvable.");
            return "views/preter/verification_pret";
        }

        Adherent adherent = optionalAdherent.get();
        Integer idProfil = adherent.getProfil().getId_profil();
        Integer idRegle = profilService.getIdRegleByIdProfil(idProfil);

        Regle regle = (idRegle != null) ? regleService.getById(idRegle).orElse(null) : null;

        String statut = adherentService.getStatutAdherentOnDate(idAdherent, dateDebut);
        boolean disponible = exemplaireService.isExemplaireDisponible(idExemplaire);
        boolean isSanctioned = sanctionService.isAdherentSanctioned(idAdherent, dateDebut.atStartOfDay());

        int nbMaxPrets = (regle != null) ? regle.getNb_livre_preter_max() : 0;
        int nbPretsActifs = pretService.countPretsActifsParAdherentALaDate(idAdherent, dateDebut);

        // Ajuste la date de fin
        if (regle != null && dateDebut != null) {
            LocalDate dateFin = pretService.ajusterDateFin(dateDebut, regle.getNb_jour_duree_pret_max());
            pret.setDate_fin(dateFin);
            model.addAttribute("dateFin", dateFin);
        }

        model.addAttribute("dateDebut", dateDebut);
        model.addAttribute("idAdherent", idAdherent);
        model.addAttribute("idExemplaire", idExemplaire);
        model.addAttribute("idLivre", idLivre);
        model.addAttribute("ageRestriction", ageRestriction);
        model.addAttribute("ageAdherent", ageAdherent);
        model.addAttribute("statut", statut);
        model.addAttribute("disponible", disponible);
        model.addAttribute("isSanctioned", isSanctioned);
        model.addAttribute("idRegle", idRegle);
        model.addAttribute("regle", regle);

        // ✅ Enregistre le prêt uniquement si tout est valide
        if (!isSanctioned &&
                nbPretsActifs < nbMaxPrets &&
                disponible &&
                (ageRestriction == null || ageAdherent >= ageRestriction) &&
                "actif".equalsIgnoreCase(statut)) {

            pretService.savePret(pret);
            EtatExemplaire etatExemplaire = new EtatExemplaire();

            // Lier l'exemplaire emprunté
            etatExemplaire.setExemplaire(pret.getExemplaire());

            // Créer un objet Etat avec id 2 (Emprunté)
            Etat etat = new Etat();
            etat.setId_etat(2);
            etatExemplaire.setEtat(etat);

            // Date de modification = début du prêt (converti en LocalDateTime)
            etatExemplaire.setDate_modif(LocalDateTime.now());

            // Enregistrer dans la base
            etatExemplaireService.saveEtatExemplaire(etatExemplaire);

            model.addAttribute("message", "✅ Le prêt a été enregistré avec succès !");
        } else if (isSanctioned) {
            model.addAttribute("message", "❌ L'adhérent est sanctionné à cette date.");
        } else if (nbPretsActifs >= nbMaxPrets) {
            model.addAttribute("message", "❌ L'adhérent a déjà atteint la limite de prêts (" + nbMaxPrets + ").");
        } else if (!disponible) {
            model.addAttribute("message", "❌ L'exemplaire n'est pas disponible.");
        } else if (ageRestriction != null && ageAdherent < ageRestriction) {
            model.addAttribute("message",
                    "❌ L'adhérent n'a pas l'âge requis (" + ageRestriction + " ans) pour emprunter ce livre.");
        } else if (!"actif".equalsIgnoreCase(statut)) {
            model.addAttribute("message", "❌ L'adhérent est inactif à cette date.");
        }

        return "views/preter/verification_pret";
    }

    // DELETE ACTION
    @GetMapping("/delete/{id}")
    public String deletePret(@PathVariable("id") Integer id) {
        pretRepository.deleteById(id);
        return "redirect:/preter/liste";
    }

    @GetMapping("/liste")
    public String listPretsByAdherent(Model model, HttpSession session) {
        // Récupérer l'utilisateur depuis la session
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("user");

        // Si pas d'utilisateur en session, rediriger vers login (optionnel)
        if (utilisateur == null) {
            return "redirect:/login";
        }

        // Trouver l'adhérent lié à cet utilisateur
        Adherent adherent = adherentRepository.findByUtilisateur(utilisateur)
                .orElse(null);

        if (adherent == null) {
            model.addAttribute("error", "Aucun adhérent trouvé");
            return "pret/list";
        }

        // Récupérer les prêts de cet adhérent
        List<Pret> prets = pretRepository.findByAdherent(adherent);

        model.addAttribute("prets", prets);
        return "pret/list";
    }

    // Dans PretController.java
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleNotFound(IllegalArgumentException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error"; // Créez une page error.html pour afficher les messages d'erreur
    }

}
