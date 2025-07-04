package com.biblio.bibliotheque.model.gestion;

import jakarta.persistence.*;

@Entity
public class Regle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_regle;

    @Column(nullable = false)
    private Integer nb_jour_duree_pret_max;

    @Column(nullable = false)
    private Integer nb_livre_preter_max;

    @Column(nullable = false)
    private Integer nb_prolengement_pret_max;

    @Column(nullable = false)
    private Integer nb_jour_prolongement_max;

    // Getters and Setters
    public Integer getId_regle() {
        return id_regle;
    }

    public void setId_regle(Integer id_regle) {
        this.id_regle = id_regle;
    }

    public Integer getNb_jour_duree_pret_max() {
        return nb_jour_duree_pret_max;
    }

    public void setNb_jour_duree_pret_max(Integer nb_jour_duree_pret_max) {
        this.nb_jour_duree_pret_max = nb_jour_duree_pret_max;
    }

    public Integer getNb_livre_preter_max() {
        return nb_livre_preter_max;
    }

    public void setNb_livre_preter_max(Integer nb_livre_preter_max) {
        this.nb_livre_preter_max = nb_livre_preter_max;
    }

    public Integer getNb_prolengement_pret_max() {
        return nb_prolengement_pret_max;
    }

    public void setNb_prolengement_pret_max(Integer nb_prolengement_pret_max) {
        this.nb_prolengement_pret_max = nb_prolengement_pret_max;
    }

    public Integer getNb_jour_prolongement_max() {
        return nb_jour_prolongement_max;
    }

    public void setNb_jour_prolongement_max(Integer nb_jour_prolongement_max) {
        this.nb_jour_prolongement_max = nb_jour_prolongement_max;
    }
}
