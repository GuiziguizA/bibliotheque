package sid.org.service;


import java.util.Map;

import org.springframework.data.domain.Page;

import sid.org.classe.Livre;
import sid.org.exception.BibliothequeException;
import sid.org.exception.DemandeUtilisateurIncorrectException;
import sid.org.exception.EntityAlreadyExistException;


public interface LivreService {

	public Livre createLivre(Livre livre) throws EntityAlreadyExistException;
	public Map<String, Object> afficheUnLivre( Long id) throws DemandeUtilisateurIncorrectException;

	
	  public Livre modificationNombreExemplaire(Long id, int nom) throws BibliothequeException;

	/*
	 * public Map<String, Object> rechercherLivres(String recherche) throws
	 * Exception;
	 */
	public Page<Livre> searchLivres(String recherche);
	
	
	
	
	

	

	
	
	
	 


}
