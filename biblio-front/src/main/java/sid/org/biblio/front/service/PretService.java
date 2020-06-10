package sid.org.biblio.front.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;

import sid.org.biblio.front.classe.Pret;
import sid.org.biblio.front.classe.Utilisateur;

public interface PretService {

	

	public Page<Pret> pretsUtilisateur(String mail, int page, int size) throws Exception;

	public 	void creerPret(Pret pret) throws HttpStatusCodeException , RestClientException;
	



}
