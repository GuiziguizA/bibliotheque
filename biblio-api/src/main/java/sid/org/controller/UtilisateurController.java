package sid.org.controller;

import java.util.Map;

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
import sid.org.classe.Utilisateur;
import sid.org.dto.UtilisateurDto;
import sid.org.exception.BibliothequeException;
import sid.org.service.UtilisateurService;

@RestController
@Api(value="Api Utilisateurs",description = "Api Utilisateurs")
public class UtilisateurController {
	

	@Autowired
	private UtilisateurService utilisateurService;

	
	@GetMapping("/users/{id}")
	@ApiOperation(value="affiche un utilisateur en fonction de son id",response = UtilisateurController.class)
 public Utilisateur afficherUtilisateurs(@PathVariable Long id) throws BibliothequeException {
	  
	Utilisateur user=utilisateurService.voirUtilisateur(id);
		return user;
	

}
	@GetMapping("/users")
	@ApiOperation(value="affiche une Page avec tous les utilisateurs",response = UtilisateurController.class)
	 public   Page<Utilisateur> afficherUtilisateurs(@RequestParam int page, @RequestParam int size) throws BibliothequeException{
		  
		  Page<Utilisateur> users;
		 
			users=utilisateurService.voirListeUtilisateurs(page,size);
			return  users; 
			

	}
	@PostMapping("/users")
	@ApiOperation(value="ajouter un utilisateur ",response = UtilisateurController.class)
	public Utilisateur creerUtilisateur(
			  @ApiParam(value="Ajouter UtilisateurDto dans le body" , required=true)@Valid @RequestBody UtilisateurDto utilisateurDto) throws BibliothequeException{

		Utilisateur user =	utilisateurService.creerUtilisateur(utilisateurDto);
		return user;
		

	}
	
	@PutMapping("/users/{id}")
	/* @Secured(value= {"ROLE_admin"}) */
	@ApiOperation(value="modifier le statut de l'utilisateur",response = UtilisateurController.class)
	public Utilisateur modifierUtilisateur(@PathVariable Long id ,   @ApiParam(value="statut dans le body" , required=true)@Valid @RequestBody String statut) throws BibliothequeException{
		
		Utilisateur user =	utilisateurService.modifierUtilisateur(id, statut);
		return user;
		

	}
	
	/* @Secured(value= {"ROLE_admin"}) */
	@DeleteMapping("/users/{id}")
	 @ApiOperation(value="supprimer un utilisateur",response = UtilisateurController.class)
	public void supprimerUtilisateur(@PathVariable Long id) throws BibliothequeException	{
		
		

			utilisateurService.supprimerUtilisateur(id);
	
	
	}
	 

	
}