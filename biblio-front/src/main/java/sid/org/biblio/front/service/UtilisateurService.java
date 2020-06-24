package sid.org.biblio.front.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.client.HttpStatusCodeException;

import sid.org.biblio.front.classe.Utilisateur;

public interface UtilisateurService {

	public void creerUtilisateur(Utilisateur utilisateur) throws HttpStatusCodeException;

	public String identification(HttpServletRequest request) throws HttpStatusCodeException;

	public Utilisateur infosUtilisateur(String mail, String motDePasse);

	


	
}
