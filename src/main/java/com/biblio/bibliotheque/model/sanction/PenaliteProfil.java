package com.biblio.bibliotheque.model.sanction;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.biblio.bibliotheque.model.gestion.Profil;

@Entity
public class PenaliteProfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_penalite_profil;

    @Column(nullable = false)
    private LocalDateTime date_modif;

    @ManyToOne
    @JoinColumn(name = "id_penalite", nullable = false)
    private Penalite penalite;

    @ManyToOne
    @JoinColumn(name = "id_profil", nullable = false)
    private Profil profil;

    // Getters and Setters
    public Integer getId_penalite_profil() {
        return id_penalite_profil;
    }

    public void setId_penalite_profil(Integer id_penalite_profil) {
        this.id_penalite_profil = id_penalite_profil;
    }

    public LocalDateTime getDate_modif() {
        return date_modif;
    }

    public void setDate_modif(LocalDateTime date_modif) {
        this.date_modif = date_modif;
    }

    public Penalite getPenalite() {
        return penalite;
    }

    public void setPenalite(Penalite penalite) {
        this.penalite = penalite;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }
}
