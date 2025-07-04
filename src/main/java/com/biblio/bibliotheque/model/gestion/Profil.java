package com.biblio.bibliotheque.model.gestion;

import jakarta.persistence.*;

@Entity
public class Profil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_profil;

    @Column(nullable = false, length = 50)
    private String nom;

    @ManyToOne
    @JoinColumn(name = "id_regle", nullable = false)
    private Regle regle;

    // Getters and Setters
    public Integer getId_profil() {
        return id_profil;
    }

    public void setId_profil(Integer id_profil) {
        this.id_profil = id_profil;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Regle getRegle() {
        return regle;
    }

    public void setRegle(Regle regle) {
        this.regle = regle;
    }
}
