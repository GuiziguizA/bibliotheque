package sid.org.service;


import java.util.Map;

import org.springframework.data.domain.Page;

import sid.org.classe.Livre;

public interface LivreService {

	public Livre createLivre(Livre livre) throws Exception;
	public Map<String, Object> afficheUnLivre( Long id) throws Exception;
	public Map<String, Object> afficherLivres(String nom, String type);
	
	  public Livre modificationNombreExemplaire(Long id, int nom) throws Exception;
	public Map<String, Object> rechercherLivres(String recherche) throws Exception;
	public Page<Livre> searchLivres(String recherche) throws Exception;
	
	
	
	
	

	

	
	
	
	 


}
