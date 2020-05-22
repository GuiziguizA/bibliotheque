package sid.org.controller;





import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sid.org.classe.Livre;
import sid.org.dto.LivreDto;
import sid.org.exception.BibliothequeException;
import sid.org.service.LivreService;
import sid.org.specification.LivreCriteria;

@RestController
public class LivreController {
	@Autowired
	private LivreService livreService;
	
	
	
	  @GetMapping("/books") public Page<Livre>afficherLivres(@RequestBody LivreCriteria livreSearch,@RequestParam int page ,@RequestParam int size) throws Exception {
	  
		  Page<Livre> livres;
	  
	
		livres = livreService.searchLivres(livreSearch,page ,size);
		return livres; 
	
	  
	  
	  }
	  
	  
	  @GetMapping("/books/{id}")
public  Livre afficheUnLivre(@PathVariable Long id) throws BibliothequeException{
		 
		
	  Livre livre=livreService.afficheUnLivre(id);
	  return livre;
	
	 
	  
	  }
	  
	  
	  @PostMapping("/books")
	  @Secured(value= {"ROLE_admin","ROLE_employe"})
	public  Livre ajouterLivre(@Valid @RequestBody LivreDto livre) throws BibliothequeException { 
		  
		  return livreService.createLivre(livre);
		  
	  }
	 
	  @Secured(value= {"ROLE_admin"})
	  @DeleteMapping("/books/{id}")
	  public  void supprimerUnLivre1(@PathVariable Long id) throws BibliothequeException{
	  		 
	  		
	  	 livreService.supprimerLivre(id);
	  	
	  	 
	  	  
	  	  }
}
