package com.biblio.bibliotheque.model.gestion;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class StatutAdherent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idStatutAdherent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_adherent", nullable = false)
    private Adherent adherent;

    @Column(name = "nom", nullable = false, length = 20)
    private String nom;

    @Column(name = "date_debut", nullable = false)
    private LocalDate dateDebut;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    // --- Getters & Setters ---

    public Integer getIdStatutAdherent() {
        return idStatutAdherent;
    }

    public void setIdStatutAdherent(Integer idStatutAdherent) {
        this.idStatutAdherent = idStatutAdherent;
    }

    public Adherent getAdherent() {
        return adherent;
    }

    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }
}
