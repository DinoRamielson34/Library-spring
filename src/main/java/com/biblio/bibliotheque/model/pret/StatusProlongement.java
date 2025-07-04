package com.biblio.bibliotheque.model.pret;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "statusprolongement")
public class StatusProlongement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_status")
    private Integer idStatus;

    @ManyToOne
    @JoinColumn(name = "id_prolongement")
    private Prolongement prolongement;

    @Column(name = "date_prolongement", nullable = false)
    private LocalDateTime dateProlongement;

    @Column(name = "date_fin_demandee", nullable = false)
    private LocalDateTime dateFinDemandee;

    @ManyToOne
    @JoinColumn(name = "id_pret", nullable = false)
    private Pret pret;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "status_traintement", nullable = false)
    private Integer statusTraintement;

    // Getters and Setters

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }

    public Prolongement getProlongement() {
        return prolongement;
    }

    public void setProlongement(Prolongement prolongement) {
        this.prolongement = prolongement;
    }

    public LocalDateTime getDateProlongement() {
        return dateProlongement;
    }

    public void setDateProlongement(LocalDateTime dateProlongement) {
        this.dateProlongement = dateProlongement;
    }

    public Pret getPret() {
        return pret;
    }

    public void setPret(Pret pret) {
        this.pret = pret;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatusTraintement() {
        return statusTraintement;
    }

    public void setStatusTraintement(Integer statusTraintement) {
        this.statusTraintement = statusTraintement;
    }

    public LocalDateTime getDateFinDemandee() {
        return dateFinDemandee;
    }

    public void setDateFinDemandee(LocalDateTime dateFinDemandee) {
        this.dateFinDemandee = dateFinDemandee;
    }
}