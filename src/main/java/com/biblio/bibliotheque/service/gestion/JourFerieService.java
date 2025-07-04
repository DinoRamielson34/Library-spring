package com.biblio.bibliotheque.service.gestion;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblio.bibliotheque.model.gestion.JourFerie;
import com.biblio.bibliotheque.repository.gestion.JourFerieRepository;

@Service
public class JourFerieService {

    @Autowired
    private JourFerieRepository jourFerieRepository;

    public List<JourFerie> getAll() {
        return jourFerieRepository.findAll();
    }

    public Optional<JourFerie> getById(Integer id) {
        return jourFerieRepository.findById(id);
    }

    public JourFerie save(JourFerie jourFerie) {
        return jourFerieRepository.save(jourFerie);
    }

    public void delete(Integer id) {
        jourFerieRepository.deleteById(id);
    }


    public boolean isWeekend(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }

    public List<JourFerie> getAllJourFerie() {
        return jourFerieRepository.findAll();
    }

    public List<LocalDate> getDatesJourFerie() {
        return jourFerieRepository.getDatesJourFerie();
    }

    public boolean isJourFerie(LocalDate date) {
        List<LocalDate> joursFeries = getDatesJourFerie();
        return joursFeries.contains(date);
    }

    
    
}
