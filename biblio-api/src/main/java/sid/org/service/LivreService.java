package sid.org.service;


import java.util.Map;




import sid.org.classe.Livre;

public interface LivreService {

	public Livre createLivre(Livre livre);
	public Map<String, Object> afficheUnLivre( Long id) throws Exception;
	public Map<String, Object> afficherLivres(String nom, String type);
	
	  public Livre modificationNombreExemplaire(Long id) throws Exception;
	public Map<String, Object> rechercherLivres(String recherche) throws Exception;
	
	
	

	

	
	
	
	 


}
