package sid.org.biblio.front.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import sid.org.biblio.front.classe.Livre;

public interface BookService {

	public Livre livre(String id) throws Exception;

	public void createLivre(Livre livre) throws Exception;

	public Page<Livre> livresRecherche(Optional<String> type, Optional<String> recherche, int  size, int page);

}
