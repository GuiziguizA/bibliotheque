package sid.org.biblio.front.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sid.org.biblio.front.classe.Livre;
import sid.org.biblio.front.config.UriConstants;
import sid.org.biblio.front.service.BookService;
import sid.org.biblio.front.service.BooksServiceImpl;


@Controller
@RequestMapping(UriConstants.CONTROLEUR_LAMBDA)
public class LivreController {
	@Autowired
	private BookService bookService;

    @GetMapping(value = "/books/{id}")
    public String Book(Model model,@PathVariable("id")String id) {
      
    	Livre book =bookService.livre(id);
    	model.addAttribute("book",book);
        return "book";
    }
}
