package com.biblio.bibliotheque.model.livre;

import jakarta.persistence.*;

@Entity
public class Etat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_etat;

    @Column(nullable = false, length = 50)
    private String nom;

    // Getters and Setters
    public Integer getId_etat() {
        return id_etat;
    }

    public void setId_etat(Integer id_etat) {
        this.id_etat = id_etat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}

