package sid.org.biblio.front.service;

import org.springframework.web.client.HttpStatusCodeException;

import sid.org.biblio.front.classe.Utilisateur;

public interface UtilisateurService {

	public void creerUtilisateur(Utilisateur utilisateur) throws HttpStatusCodeException;

}
