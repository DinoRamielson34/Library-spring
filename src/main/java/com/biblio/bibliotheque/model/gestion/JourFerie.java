package com.biblio.bibliotheque.model.gestion;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class JourFerie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_jour_ferie;

    @Column(length = 50)
    private String description;

    @Column(name = "date_jf", nullable = false)
    private LocalDate dateJf;

    // Getters and Setters
    public Integer getId_jour_ferie() {
        return id_jour_ferie;
    }

    public void setId_jour_ferie(Integer id_jour_ferie) {
        this.id_jour_ferie = id_jour_ferie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateJf() {
        return dateJf;
    }

    public void setDateJf(LocalDate dateJf) {
        this.dateJf = dateJf;
    }
}
