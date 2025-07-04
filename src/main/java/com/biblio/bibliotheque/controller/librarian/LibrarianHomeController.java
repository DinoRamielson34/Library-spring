
package com.biblio.bibliotheque.controller.librarian;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/librarian")
public class LibrarianHomeController {

    @GetMapping("/home")
    public String home() {
        return "librarian/home";
    }
}
