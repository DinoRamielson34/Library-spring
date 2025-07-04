package com.biblio.bibliotheque.model.gestion;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RegleJourFerie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_regle_jour_ferie;

    @Column(name = "comportement", nullable = false)
    private Integer comportement;

    @Column(name = "date_modif", nullable = false)
    private LocalDateTime dateModif;

    // Getters and Setters
    public Integer getId_regle_jour_ferie() {
        return id_regle_jour_ferie;
    }

    public void setId_regle_jour_ferie(Integer id_regle_jour_ferie) {
        this.id_regle_jour_ferie = id_regle_jour_ferie;
    }

    public Integer getComportement() {
        return comportement;
    }

    public void setComportement(Integer comportement) {
        this.comportement = comportement;
    }

    public LocalDateTime getDateModif() {
        return dateModif;
    }

    public void setDateModif(LocalDateTime dateModif) {
        this.dateModif = dateModif;
    }
}
