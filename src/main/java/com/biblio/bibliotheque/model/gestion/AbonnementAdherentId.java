package com.biblio.bibliotheque.model.gestion;

import java.io.Serializable;
import java.util.Objects;

public class AbonnementAdherentId implements Serializable {
    private Integer adherent;
    private Integer abonnement;

    public AbonnementAdherentId() {
    }

    public AbonnementAdherentId(Integer adherent, Integer abonnement) {
        this.adherent = adherent;
        this.abonnement = abonnement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof AbonnementAdherentId))
            return false;
        AbonnementAdherentId that = (AbonnementAdherentId) o;
        return Objects.equals(adherent, that.adherent) &&
                Objects.equals(abonnement, that.abonnement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adherent, abonnement);
    }

}
