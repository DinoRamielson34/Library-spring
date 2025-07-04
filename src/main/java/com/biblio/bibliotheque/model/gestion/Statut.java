package com.biblio.bibliotheque.model.gestion;

import jakarta.persistence.*;

@Entity
public class Statut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_statut;

    @Column(nullable = false, unique = true, length = 50)
    private String nom;

    // Getters and Setters
    public Integer getId_statut() {
        return id_statut;
    }

    public void setId_statut(Integer id_statut) {
        this.id_statut = id_statut;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
