package sid.org.service;


import java.util.Map;

import org.springframework.data.domain.Page;

import sid.org.classe.Utilisateur;
import sid.org.exception.BibliothequeException;
import sid.org.exception.DemandeUtilisateurIncorrectException;

public interface UtilisateurService {
	
	public Utilisateur creerUtilisateur(Utilisateur utilisateur) throws BibliothequeException;
	public	Utilisateur modifierUtilisateur(Long id, String motDePasse) throws DemandeUtilisateurIncorrectException;
	public void supprimerUtilisateur(Long id) throws DemandeUtilisateurIncorrectException;
	public Page<Utilisateur> voirListeUtilisateurs(int page, int size);
	public Map<String, Object> voirUtilisateur(Long id) throws DemandeUtilisateurIncorrectException ;
	
	
}
