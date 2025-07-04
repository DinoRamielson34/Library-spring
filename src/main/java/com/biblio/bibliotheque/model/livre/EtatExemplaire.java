package com.biblio.bibliotheque.model.livre;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class EtatExemplaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_etat_exemplaire;

    @Column(nullable = false)
    private LocalDateTime date_modif;

    @ManyToOne
    @JoinColumn(name = "id_exemplaire", nullable = false)
    private Exemplaire exemplaire;

    @ManyToOne
    @JoinColumn(name = "id_etat", nullable = false)
    private Etat etat;

    // Getters and Setters
    public Integer getId_etat_exemplaire() {
        return id_etat_exemplaire;
    }

    public void setId_etat_exemplaire(Integer id_etat_exemplaire) {
        this.id_etat_exemplaire = id_etat_exemplaire;
    }

    public LocalDateTime getDate_modif() {
        return date_modif;
    }

    public void setDate_modif(LocalDateTime date_modif) {
        this.date_modif = date_modif;
    }

    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }
}
