package sid.org.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sid.org.classe.Utilisateur;
import sid.org.dto.UtilisateurDto;
import sid.org.exception.BibliothequeException;
import sid.org.service.UtilisateurService;

@RestController
public class UtilisateurController {
	

	@Autowired
	private UtilisateurService utilisateurService;

	
	@GetMapping("/users/{id}")
 public Utilisateur afficherUtilisateurs(@PathVariable Long id) throws BibliothequeException {
	  
	Utilisateur user=utilisateurService.voirUtilisateur(id);
		return user;
	

}
	@GetMapping("/users")
	 public   Page<Utilisateur> afficherUtilisateurs(@RequestParam int page, @RequestParam int size) throws BibliothequeException{
		  
		  Page<Utilisateur> users;
		 
			users=utilisateurService.voirListeUtilisateurs(page,size);
			return  users; 
			

	}
	@PostMapping("/users")
	public Utilisateur creerUtilisateur(@Valid @RequestBody UtilisateurDto utilisateurDto) throws BibliothequeException{

		Utilisateur user =	utilisateurService.creerUtilisateur(utilisateurDto);
		return user;
		

	}
	
	@PutMapping("/users/{id}")
	 @Secured(value= {"ROLE_admin"})
	public Utilisateur modifierUtilisateur(@PathVariable Long id , @Valid @RequestBody String motDePasse) throws BibliothequeException{
		
		Utilisateur user =	utilisateurService.modifierUtilisateur(id, motDePasse);
		return user;
		

	}
	
	 @Secured(value= {"ROLE_admin"})
	@DeleteMapping("/users/{id}")
	public void supprimerUtilisateur(@PathVariable Long id) throws BibliothequeException	{
		
		

			utilisateurService.supprimerUtilisateur(id);
	
	
	}
	 

	
}