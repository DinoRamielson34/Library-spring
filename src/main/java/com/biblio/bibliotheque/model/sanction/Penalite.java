package com.biblio.bibliotheque.model.sanction;

import jakarta.persistence.*;

@Entity
public class Penalite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_penalite;

    @Column(nullable = false)
    private Integer nb_jour_de_penalite;

    public Integer getId_penalite() {
        return id_penalite;
    }

    public void setId_penalite(Integer id_penalite) {
        this.id_penalite = id_penalite;
    }

    public Integer getNb_jour_de_penalite() {
        return nb_jour_de_penalite;
    }

    public void setNb_jour_de_penalite(Integer nb_jour_de_penalite) {
        this.nb_jour_de_penalite = nb_jour_de_penalite;
    }
}

