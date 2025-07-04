package com.biblio.bibliotheque.model.pret;

import com.biblio.bibliotheque.model.livre.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.biblio.bibliotheque.model.livre.Exemplaire;
import com.biblio.bibliotheque.model.livre.Type;
import com.biblio.bibliotheque.model.gestion.Adherent;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Pret {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pret;

    @Column
    private LocalDate date_debut;

    @Column
    private LocalDate date_fin;

    @ManyToOne
    @JoinColumn(name = "id_exemplaire", nullable = false)
    private Exemplaire exemplaire;

    @ManyToOne
    @JoinColumn(name = "id_adherent", nullable = false)
    private Adherent adherent;

    @ManyToOne
    @JoinColumn(name = "id_type", nullable = false)
    private Type type;
    // Getters and Setters

    public Integer getId_pret() {
        return id_pret;
    }

    public void setId_pret(Integer id_pret) {
        this.id_pret = id_pret;
    }

    public LocalDate getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(LocalDate date_debut) {
        this.date_debut = date_debut;
    }

    public LocalDate getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(LocalDate date_fin) {
        this.date_fin = date_fin;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }


}
