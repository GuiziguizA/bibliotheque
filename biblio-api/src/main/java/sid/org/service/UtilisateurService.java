package sid.org.service;




import org.springframework.data.domain.Page;

import sid.org.classe.Utilisateur;
import sid.org.dto.UtilisateurDto;

import sid.org.exception.EntityAlreadyExistException;
import sid.org.exception.ResultNotFoundException;

public interface UtilisateurService {
	
	public Utilisateur creerUtilisateur(UtilisateurDto utilisateurDto) throws EntityAlreadyExistException;
	public	Utilisateur modifierUtilisateur(Long id, String motDePasse) throws ResultNotFoundException;
	public void supprimerUtilisateur(Long id) throws ResultNotFoundException;
	public Page<Utilisateur> voirListeUtilisateurs(int page, int size) throws  ResultNotFoundException;
	public Utilisateur voirUtilisateur(Long id) throws ResultNotFoundException ;
	
	
	
}
