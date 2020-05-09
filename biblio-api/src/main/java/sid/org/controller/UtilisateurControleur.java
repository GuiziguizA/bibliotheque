package sid.org.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sid.org.classe.Utilisateur;
import sid.org.service.UtilisateurService;

@RestController
public class UtilisateurControleur {
	

	@Autowired
	private UtilisateurService utilisateurService;
	
	@GetMapping("/users/{id}")
 public Map<String, Object>afficherUtilisateurs(@PathVariable Long id) throws Exception {
	  
	  Map<String, Object> user;
	  
		user=utilisateurService.voirUtilisateur(id);
		return user;
	

}
	@GetMapping("/users")
	 public Map<String, Object>afficherUtilisateurs() throws Exception {
		  
		  Map<String, Object> users;
		 
			users=utilisateurService.voirListeUtilisateurs();
			return  users; 
			

	}
	@PostMapping("/users")
	public Utilisateur creerUtilisateur(@RequestBody Utilisateur utilisateur) throws Exception{

		Utilisateur user =	utilisateurService.creerUtilisateur(utilisateur);
		return user;
		

	}
	
	@PutMapping("/users/{id}")
	public Utilisateur modifierUtilisateur(@PathVariable Long id ,@RequestBody String motDePasse) throws Exception{
		
		Utilisateur user =	utilisateurService.modifierUtilisateur(id, motDePasse);
		return user;
		

	}
	
	
	@DeleteMapping("/users/{id}")
	public void supprimerUtilisateur(@PathVariable Long id) throws Exception{
		
		

			utilisateurService.supprimerUtilisateur(id);
	
	
	}
	
	
}