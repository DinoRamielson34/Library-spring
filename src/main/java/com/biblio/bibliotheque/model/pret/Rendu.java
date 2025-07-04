package com.biblio.bibliotheque.model.pret;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Rendu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_rendu;

    @Column(name = "date_du_rendu", nullable = false)
    private LocalDate dateDuRendu;

    @OneToOne
    @JoinColumn(name = "id_pret", nullable = false, unique = true)
    private Pret pret;
    
    public Rendu(LocalDate dateRendu, Pret pret) {
        this.dateDuRendu = dateRendu;
        this.pret = pret;
    }
    
    public Rendu() {
        //TODO Auto-generated constructor stub
    }

    // Getters and Setters
    public Integer getId_rendu() {
        return id_rendu;
    }

    public void setId_rendu(Integer id_rendu) {
        this.id_rendu = id_rendu;
    }

    public LocalDate getDateDuRendu() {
        return dateDuRendu;
    }

    public void setDateDuRendu(LocalDate dateDuRendu) {
        this.dateDuRendu = dateDuRendu;
    }

    public Pret getPret() {
        return pret;
    }

    public void setPret(Pret pret) {
        this.pret = pret;
    }
}
