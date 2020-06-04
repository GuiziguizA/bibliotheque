package sid.org.biblio.front.service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import sid.org.biblio.front.classe.Livre;
import sid.org.biblio.front.classe.Pret;
import sid.org.biblio.front.classe.Utilisateur;

@Service
public class PretServiceImpl implements PretService {
	
	@Override
	public Page<Pret> pretsUtilisateur(String mail,int page,int size) throws Exception{
		
		RestTemplate rt = new RestTemplate();
		String page1 = Integer.toString(page); 
	    String size1 = Integer.toString(size); 
		final String uri = "http://localhost:8081/prets?mail="+mail+"&page="+page1+"&size="+size1;
		ParameterizedTypeReference<RestReponsePage<Pret>> responseType = new ParameterizedTypeReference<RestReponsePage<Pret>>() { };

		ResponseEntity<RestReponsePage<Pret>> result = rt.exchange(uri, HttpMethod.GET, null,responseType);

		Page<Pret> prets = result.getBody();
	
		
		return prets;
	}

}
