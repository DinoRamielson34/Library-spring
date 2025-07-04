package com.biblio.bibliotheque.service.pret;

import com.biblio.bibliotheque.model.pret.Pret;
import com.biblio.bibliotheque.model.pret.Prolongement;
import com.biblio.bibliotheque.model.pret.StatusProlongement;
import com.biblio.bibliotheque.repository.pret.PretRepository;
import com.biblio.bibliotheque.repository.pret.ProlongementRepository;
import com.biblio.bibliotheque.repository.pret.StatusProlongementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LibrarianProlongementService {

    @Autowired
    private StatusProlongementRepository statusProlongementRepository;

    @Autowired
    private ProlongementRepository prolongementRepository;

    @Autowired
    private PretRepository pretRepository;

    public List<StatusProlongement> getPendingProlongationRequests() {
        return statusProlongementRepository.findByStatusTraintement(0);
    }

    public void acceptProlongationRequest(Integer statusProlongementId) throws Exception {
        StatusProlongement statusProlongement = statusProlongementRepository.findById(statusProlongementId)
                .orElseThrow(() -> new Exception("Demande de prolongation non trouvée."));

        if (statusProlongement.getStatusTraintement() == 1) {
            throw new Exception("Cette demande a déjà été traitée.");
        }

        // Créer un nouveau prolongement
        Prolongement prolongement = new Prolongement();
        prolongement.setPret(statusProlongement.getPret());
        prolongement.setDateProlongement(LocalDateTime.now());
        prolongement.setNouveauDateFinPret(statusProlongement.getDateFinDemandee());
        Prolongement savedProlongement = prolongementRepository.save(prolongement);

        // Mettre à jour le statut de la demande
        statusProlongement.setStatus(1); // Acceptée
        statusProlongement.setStatusTraintement(1); // Traitée
        statusProlongement.setProlongement(savedProlongement);
        statusProlongementRepository.save(statusProlongement);

        // Mettre à jour la date de fin du prêt original
        Pret pret = statusProlongement.getPret();
        pret.setDate_fin(statusProlongement.getDateFinDemandee().toLocalDate());
        pretRepository.save(pret);
    }

    public void refuseProlongationRequest(Integer statusProlongementId) throws Exception {
        StatusProlongement statusProlongement = statusProlongementRepository.findById(statusProlongementId)
                .orElseThrow(() -> new Exception("Demande de prolongation non trouvée."));

        if (statusProlongement.getStatusTraintement() == 1) {
            throw new Exception("Cette demande a déjà été traitée.");
        }

        statusProlongement.setStatus(0); // Refusée (reste à 0)
        statusProlongement.setStatusTraintement(1); // Traitée
        statusProlongementRepository.save(statusProlongement);
    }
}
