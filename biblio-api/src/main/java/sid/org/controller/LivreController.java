package sid.org.controller;




import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sid.org.classe.Livre;
import sid.org.service.ILivre;

@RestController
public class LivreController {
	@Autowired
	private ILivre livreService;
	
	
	
	  @GetMapping("/books") public Map<String, Object>afficherLivres(@RequestParam(required = false) String nom,@RequestParam(required = false) String type) {
	  
	  Map<String, Object> livres;
	  
	
		livres = livreService.afficherLivres(nom, type);
		return livres; 
	
	  
	  
	  }
	  
	  
	  @GetMapping("/books/{id}")
public  Map<String, Object>afficheUnLivre(@PathVariable Long id) throws Exception{
		  Map<String, Object> livre;
		
	  livre=livreService.afficheUnLivre(id);
	  return livre;
	
	 
	  
	  }
	  
	  
	  @PostMapping("/books")
	  Livre ajouterLivre( Livre livre) { 
		  
		  return livreService.createLivre(livre);
		  
	  }
	 
	
		
}
