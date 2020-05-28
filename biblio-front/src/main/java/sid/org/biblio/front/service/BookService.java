package sid.org.biblio.front.service;

import org.springframework.http.ResponseEntity;

import sid.org.biblio.front.classe.Livre;

public interface BookService {

	public Livre livre(String id);

	public void createLivre(Livre livre);

}
