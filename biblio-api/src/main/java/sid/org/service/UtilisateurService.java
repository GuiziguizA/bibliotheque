package sid.org.service;


import java.util.Map;

import sid.org.classe.Utilisateur;

public interface UtilisateurService {
	
	public Utilisateur creerUtilisateur(Utilisateur utilisateur) throws Exception;
	public	Utilisateur modifierUtilisateur(Long id, String motDePasse) throws Exception;
	public void supprimerUtilisateur(Long id) throws Exception;
	public Map<String, Object> voirListeUtilisateurs() throws Exception;
	public Map<String, Object> voirUtilisateur(Long id) throws Exception;
	
}
