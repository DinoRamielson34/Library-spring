package com.biblio.bibliotheque.model.gestion;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class Abonnement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_abonnement;

    @Column(nullable = false)
    private Integer mois;

    @Column(nullable = false)
    private Integer annee;

    @Column(nullable = false, precision = 25, scale = 2)
    private BigDecimal tarif;

    // Getters and Setters
    public Integer getId_abonnement() {
        return id_abonnement;
    }

    public void setId_abonnement(Integer id_abonnement) {
        this.id_abonnement = id_abonnement;
    }

    public Integer getMois() {
        return mois;
    }

    public void setMois(Integer mois) {
        this.mois = mois;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public BigDecimal getTarif() {
        return tarif;
    }

    public void setTarif(BigDecimal tarif) {
        this.tarif = tarif;
    }
}
