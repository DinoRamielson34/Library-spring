package com.biblio.bibliotheque.model.reservation;

import jakarta.persistence.*;
import java.time.LocalDate;

import com.biblio.bibliotheque.model.livre.Exemplaire;
import com.biblio.bibliotheque.model.gestion.Adherent;


@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_reservation;

    @Column(nullable = false)
    private LocalDate date_reservation;

    @Column(nullable = false)
    private LocalDate date_debut_reservation;

    @Column(nullable = false)
    private LocalDate date_fin_reservation;

    @ManyToOne
    @JoinColumn(name = "id_exemplaire", nullable = false)
    private Exemplaire exemplaire;

    @ManyToOne
    @JoinColumn(name = "id_adherent", nullable = false)
    private Adherent adherent;

    // Getters and Setters
    public Integer getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(Integer id_reservation) {
        this.id_reservation = id_reservation;
    }

    public LocalDate getDate_reservation() {
        return date_reservation;
    }

    public void setDate_reservation(LocalDate date_reservation) {
        this.date_reservation = date_reservation;
    }

    public LocalDate getDate_debut_reservation() {
        return date_debut_reservation;
    }

    public void setDate_debut_reservation(LocalDate date_debut_reservation) {
        this.date_debut_reservation = date_debut_reservation;
    }

    public LocalDate getDate_fin_reservation() {
        return date_fin_reservation;
    }

    public void setDate_fin_reservation(LocalDate date_fin_reservation) {
        this.date_fin_reservation = date_fin_reservation;
    }

    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }

    public Adherent getAdherent() {
        return adherent;
    }

    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }
}
