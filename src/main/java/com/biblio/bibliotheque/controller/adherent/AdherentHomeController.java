
package com.biblio.bibliotheque.controller.adherent;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adherent")
public class AdherentHomeController {

    @GetMapping("/home")
    public String home() {
        return "adherent/home";
    }
}
