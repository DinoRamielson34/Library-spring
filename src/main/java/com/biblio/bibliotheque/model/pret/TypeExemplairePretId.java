package com.biblio.bibliotheque.model.pret;

import java.io.Serializable;
import java.util.Objects;

public class TypeExemplairePretId implements Serializable {
    private Integer exemplaire;
    private Integer type;

    public TypeExemplairePretId() {
    }

    public TypeExemplairePretId(Integer exemplaire, Integer type) {
        this.exemplaire = exemplaire;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof TypeExemplairePretId))
            return false;
        TypeExemplairePretId that = (TypeExemplairePretId) o;
        return Objects.equals(exemplaire, that.exemplaire) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exemplaire, type);
    }
}
