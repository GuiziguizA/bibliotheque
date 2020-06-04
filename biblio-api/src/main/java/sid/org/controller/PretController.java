package sid.org.controller;




import java.security.Principal;

import javax.transaction.Transactional;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import sid.org.classe.Pret;
import sid.org.dto.PretDto;
import sid.org.service.LivreService;
import sid.org.service.PretService;

@RestController
@Api(value="Api Prets",description = "Api Prets")
public class PretController {
@Autowired
	 private PretService pretService;
@Autowired
	private LivreService livreService;




@GetMapping("/prets/{id}")
@ApiOperation(value="affiche un pret en fonction de son id",response = PretController.class)
public Pret afficherUnPret(@PathVariable Long id) throws Exception{
 

	 Pret pret = pretService.afficherPret(id);
	return pret;


  
  	}





	/* @Secured(value= {"ROLE_admin","ROLE_employe"}) */
	  @PostMapping("/pret")
	@ApiOperation(value="ajout d'un nouveau pret et decrementation le stock du livre",response = PretController.class)
	  public  Pret creerUnPret(
			  @ApiParam(value="Ajouter PretDto dans le body" , required=true)@Valid @RequestBody PretDto  pretDto,Principal principal) throws Exception{
	 		Pret pret1=pretService.creerPret(pretDto,principal);
			return pret1 ;
	
	  
	
	  	  
	  	  }

	/* @Secured(value= {"ROLE_admin","ROLE_employe"}) */
@DeleteMapping("/prets/{id}")
 @ApiOperation(value="supprime le pret et reincremente le stock du livre",response = PretController.class)
public  void supprimerUnPret(@PathVariable Long id) throws Exception {

		pretService.supprimerPret(id);
	
	
}
 	
	
@GetMapping("/prets")
@ApiOperation(value="affiche Les pret d'un utilisateur",response = PretController.class)
public Page<Pret> afficherPretUtilisateur(@RequestParam String mail, @RequestParam int page, @RequestParam int size) throws Exception{
 
Page<Pret>prets=pretService.afficherPrets(mail, page, size);
	
	return prets;


  
  	}
	
}
