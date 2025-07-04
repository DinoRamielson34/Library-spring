package com.biblio.bibliotheque.controller.livre;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.biblio.bibliotheque.model.livre.Livre;
import com.biblio.bibliotheque.repository.livre.*;

@Controller
@RequestMapping("/livres")
public class LivreController {

    @Autowired
    private LivreRepository livreRepository;

    
}
