package sid.org.controller;




import javax.transaction.Transactional;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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




@GetMapping("/prets/{id}")
public Pret afficherUnPret(@PathVariable Long id) throws Exception{
 

	 Pret pret = pretService.afficherPret(id);
	return pret;


  
  	}




		@Transactional
	@Secured(value= {"ROLE_admin","ROLE_employe"})
	  @PostMapping("/prets")
	  public  Pret creerUnPret(@Valid @RequestBody Pret pret,@RequestParam(required=false) int nombre) throws Exception{
	 		Pret pret1=pretService.creerPret(pret);
           livreService.modificationNombreExemplaire(pret1.getLivre().getCodeLivre(),nombre);
			return pret1 ;
	
	  
	
	  	  
	  	  }
 @Secured(value= {"ROLE_admin","ROLE_employe"})	  
@DeleteMapping("/prets")
public  void supprimerUnPret(@PathVariable Long id) throws Exception {

		pretService.supprimerPret(id);
	
	
}
 	@Secured(value= {"ROLE_admin","ROLE_employe"})
	@PutMapping("/prets/{id}")  
public  Pret modifierUnPret(@PathVariable Long id) throws Exception{
		  	
		
	
		return  pretService.modifierPret(id);
		

  	  
  	  }

	
	
	
}
