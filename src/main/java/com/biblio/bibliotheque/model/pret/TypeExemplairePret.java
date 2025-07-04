package com.biblio.bibliotheque.model.pret;

import jakarta.persistence.*;

import com.biblio.bibliotheque.model.livre.Exemplaire;
import com.biblio.bibliotheque.model.livre.Type;

@Entity
@IdClass(TypeExemplairePretId.class)
public class TypeExemplairePret {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_exemplaire", nullable = false)
    private Exemplaire exemplaire;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_type", nullable = false)
    private Type type;

    // Getters and Setters
    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}