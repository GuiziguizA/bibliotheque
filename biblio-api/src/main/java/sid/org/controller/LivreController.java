package sid.org.controller;




import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sid.org.classe.Livre;
import sid.org.service.LivreService;

@RestController
public class LivreController {
	@Autowired
	private LivreService livreService;
	
	
	
	  @GetMapping("api/books") public Map<String, Object>afficherLivres(@RequestParam(required = false) String search) throws Exception {
	  
	  Map<String, Object> livres;
	  
	
		livres = livreService.rechercherLivres(search);
		return livres; 
	
	  
	  
	  }
	  
	  
	  @GetMapping("api/books/{id}")
public  Map<String, Object>afficheUnLivre(@PathVariable Long id) throws Exception{
		  Map<String, Object> livre;
		
	  livre=livreService.afficheUnLivre(id);
	  return livre;
	
	 
	  
	  }
	  
	  
	  @PostMapping("api/books")
	  Livre ajouterLivre( Livre livre) { 
		  
		  return livreService.createLivre(livre);
		  
	  }
	 
	
		
}
