package sid.org.biblio.front.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpStatusCodeException;

import sid.org.biblio.front.classe.Utilisateur;
import sid.org.biblio.front.service.UtilisateurService;

@Controller
public class UtilisateurController {
	@Autowired
	private UtilisateurService utilisateurService;

	@GetMapping("/users/form")
	public String afficherForm(Utilisateur utilisateur) {

		return "formulaireUtilisateur";

	}

	@PostMapping("/users")
	public String creerUtilisateur(Utilisateur utilisateur, Model model) {
		try {
			utilisateurService.creerUtilisateur(utilisateur);
			return "home";
		} catch (HttpStatusCodeException e) {
			model.addAttribute("error", e);
			return "error";
		}

	}
	
	@GetMapping("/login")
	public String afficherLogin() {

		return "login";

	}
	
	
}
