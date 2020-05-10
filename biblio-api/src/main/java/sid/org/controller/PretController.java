package sid.org.controller;



import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sid.org.classe.Pret;
import sid.org.service.LivreService;
import sid.org.service.PretService;

@RestController
public class PretController {
@Autowired
	 private PretService pretService;
@Autowired
	private LivreService livreService;




@GetMapping("prets/{id}")
public Map<String,Object>  afficherUnPret(@PathVariable Long id) throws Exception{
 

	 Map<String,Object> pret = pretService.afficherPret(id);
	return pret;


  
  	}




		@Transactional
	  @PostMapping("prets")
	  public  Pret creerUnPret(@RequestBody Pret pret) throws Exception{
	 		Pret pret1=pretService.creerPret(pret);
           livreService.modificationNombreExemplaire(pret1.getLivre().getCodeLivre());
			return pret1 ;
	
	  
	
	  	  
	  	  }
	  
@DeleteMapping("prets")
public  void supprimerUnPret(@PathVariable Long id) throws Exception {

		pretService.supprimerPret(id);
	
	
}
	@PutMapping("prets/{id}")  
public  Pret modifierUnPret(@PathVariable Long id) throws Exception{
		  	
		
	
		return  pretService.modifierPret(id);
		

  	  
  	  }

	
	
	
}
