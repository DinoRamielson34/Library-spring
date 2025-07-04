package com.biblio.bibliotheque.service.pret;

import com.biblio.bibliotheque.model.pret.Pret;
import com.biblio.bibliotheque.model.pret.Prolongement;
import com.biblio.bibliotheque.model.pret.StatusProlongement;
import com.biblio.bibliotheque.repository.pret.PretRepository;
import com.biblio.bibliotheque.repository.pret.ProlongementRepository;
import com.biblio.bibliotheque.repository.pret.StatusProlongementRepository;
import com.biblio.bibliotheque.repository.reservation.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProlongementService {

    @Autowired
    private ProlongementRepository prolongementRepository;

    @Autowired
    private PretRepository pretRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private StatusProlongementRepository statusProlongementRepository;

    public List<Prolongement> getAllProlongements() {
        return prolongementRepository.findAll();
    }

    public Optional<Prolongement> getProlongementById(Integer id) {
        return prolongementRepository.findById(id);
    }

    public Prolongement saveProlongement(Prolongement prolongement) {
        return prolongementRepository.save(prolongement);
    }

    public void deleteProlongement(Integer id) {
        prolongementRepository.deleteById(id);
    }

    public void demanderProlongement(Integer pretId, LocalDateTime dateFinDemandee) throws Exception {
        Pret pret = pretRepository.findById(pretId)
                .orElseThrow(() -> new Exception("Prêt non trouvé"));

        if (reservationRepository.existsByExemplaire(pret.getExemplaire())) {
            throw new Exception("L'exemplaire est déjà réservé.");
        }

        if (statusProlongementRepository.existsByPretAndStatusTraintement(pret, 0)) {
            throw new Exception("Une demande de prolongation est déjà en cours pour ce prêt.");
        }

        // Vérifier le nombre maximal de prolongements autorisés
        int nbProlongementMax = 0;
        int nbJoursProlongementMax = 0;

        if (pret.getAdherent() != null && pret.getAdherent().getProfil() != null && pret.getAdherent().getProfil().getRegle() != null) {
            nbProlongementMax = pret.getAdherent().getProfil().getRegle().getNb_prolengement_pret_max();
            nbJoursProlongementMax = pret.getAdherent().getProfil().getRegle().getNb_jour_prolongement_max();
        }

        long currentProlongations = statusProlongementRepository.countByPretAndStatus(pret, 1); // Compter les prolongations acceptées

        if (currentProlongations >= nbProlongementMax) {
            throw new Exception("Nombre maximal de prolongements atteint pour ce prêt (" + nbProlongementMax + ").");
        }

        long joursProlongation = java.time.temporal.ChronoUnit.DAYS.between(pret.getDate_fin(), dateFinDemandee);
        if (joursProlongation <= 0) {
            throw new Exception("La nouvelle date de fin doit être après la date de fin actuelle.");
        }

        if (joursProlongation > nbJoursProlongementMax) {
            throw new Exception("La durée de prolongation ne peut pas dépasser " + nbJoursProlongementMax + " jours.");
        }

        StatusProlongement statusProlongement = new StatusProlongement();
        statusProlongement.setPret(pret);
        statusProlongement.setDateProlongement(LocalDateTime.now());
        statusProlongement.setDateFinDemandee(dateFinDemandee);
        statusProlongement.setStatus(0);
        statusProlongement.setStatusTraintement(0);

        statusProlongementRepository.save(statusProlongement);
    }
}
