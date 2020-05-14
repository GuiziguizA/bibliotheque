package sid.org.controller;




import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
	
	
	
	  @GetMapping("books") public Page<Livre>afficherLivres(@RequestParam(required = false) String search) throws Exception {
	  
		  Page<Livre> livres;
	  
	
		livres = livreService.searchLivres(search);
		return livres; 
	
	  
	  
	  }
	  
	  
	  @GetMapping("books/{id}")
public  Map<String, Object>afficheUnLivre(@PathVariable Long id) throws Exception{
		  Map<String, Object> livre;
		
	  livre=livreService.afficheUnLivre(id);
	  return livre;
	
	 
	  
	  }
	  
	  
	  @PostMapping("books")
	  Livre ajouterLivre( Livre livre) throws Exception { 
		  
		  return livreService.createLivre(livre);
		  
	  }
	 
	
		
}
