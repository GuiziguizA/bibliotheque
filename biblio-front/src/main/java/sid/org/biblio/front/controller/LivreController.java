package sid.org.biblio.front.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import sid.org.biblio.front.classe.Livre;

import sid.org.biblio.front.service.BookService;



@Controller

@RequestMapping("/bibliotheque")
public class LivreController {
	@Autowired
	private BookService bookService;

    @GetMapping(value = "/books/{id}")
    public String Book(Model model,@PathVariable("id")String id) {
      
    	Livre book =bookService.livre(id);
    	model.addAttribute("book",book);
        return "book";
    }
    
    @GetMapping(value = "/books/add")
    public String Book(Livre livre,Model model) {
     
        return "formulaireLivre";
    }
    
    @PostMapping(value = "/books")
    public String createBook(Livre livre) {
      
    	bookService.createLivre(livre);
    	
      return "home"  ;
    }
    
    
}
