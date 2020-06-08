package sid.org.biblio.front.service;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import sid.org.biblio.front.classe.Livre;
import sid.org.biblio.front.classe.LivreCriteria;

public interface BookService {

	public Livre livre(String id) throws Exception;

	public void createLivre(Livre livre) throws Exception;

	public Page<Livre> listLivre(LivreCriteria livreCriteria, int size, int page) throws Exception;



	public Page<Livre> callApi(String type, String recherche, int size, int page);



	
	

	
	
	
	

}
