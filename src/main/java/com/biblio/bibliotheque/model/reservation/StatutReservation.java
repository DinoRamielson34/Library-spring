package com.biblio.bibliotheque.model.reservation;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.biblio.bibliotheque.model.gestion.Statut;

@Entity
public class StatutReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_statut_reservation;

    @Column(nullable = false)
    private LocalDateTime date_modif;

    @ManyToOne
    @JoinColumn(name = "id_reservation", nullable = false)
    private Reservation reservation;

    @ManyToOne
    @JoinColumn(name = "id_statut", nullable = false)
    private Statut statut;

    // Getters and Setters
    public Integer getId_statut_reservation() {
        return id_statut_reservation;
    }

    public void setId_statut_reservation(Integer id_statut_reservation) {
        this.id_statut_reservation = id_statut_reservation;
    }

    public LocalDateTime getDate_modif() {
        return date_modif;
    }

    public void setDate_modif(LocalDateTime date_modif) {
        this.date_modif = date_modif;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }
}

