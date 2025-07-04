package com.biblio.bibliotheque.model.pret;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Prolongement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_prolongement;

    @Column(name = "nouveau_date_fin_pret", nullable = false)
    private LocalDateTime nouveauDateFinPret;

    @Column(name = "date_prolongement", nullable = false)
    private LocalDateTime dateProlongement;

    @Column(name = "statut")
    private Integer statut;

    @ManyToOne
    @JoinColumn(name = "id_pret", nullable = false)
    private Pret pret;

    // Getters and Setters

    public Integer getId_prolongement() {
        return id_prolongement;
    }

    public void setId_prolongement(Integer id_prolongement) {
        this.id_prolongement = id_prolongement;
    }

    public LocalDateTime getNouveauDateFinPret() {
        return nouveauDateFinPret;
    }

    public void setNouveauDateFinPret(LocalDateTime nouveauDateFinPret) {
        this.nouveauDateFinPret = nouveauDateFinPret;
    }

    public LocalDateTime getDateProlongement() {
        return dateProlongement;
    }

    public void setDateProlongement(LocalDateTime dateProlongement) {
        this.dateProlongement = dateProlongement;
    }

    public Pret getPret() {
        return pret;
    }

    public void setPret(Pret pret) {
        this.pret = pret;
    }

    public Integer getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }
}
