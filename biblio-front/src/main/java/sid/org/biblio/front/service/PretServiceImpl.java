package sid.org.biblio.front.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import sid.org.biblio.front.classe.Pret;

@Service
public class PretServiceImpl implements PretService {

	@Value("${api.url}")
	private String apiUrl;
	@Value("${spring.api.identifiant}")
	private String identifiant;
	@Value("${spring.api.motDePasse}")
	private String motDePasse;
	@Override
	public Page<Pret> pretsUtilisateur(String mail, int page, int size,String motDePasse) throws Exception {

		RestTemplate rt = new RestTemplate();
		String page1 = Integer.toString(page);
		String size1 = Integer.toString(size);
		final String uri = apiUrl + "/prets?mail=" + mail + "&page=" + page1 + "&size=" + size1;
		ParameterizedTypeReference<RestReponsePage<Pret>> responseType = new ParameterizedTypeReference<RestReponsePage<Pret>>() {
		};

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBasicAuth(mail, motDePasse);

		ResponseEntity<RestReponsePage<Pret>> result = rt.exchange(uri, HttpMethod.GET, new HttpEntity<>(headers),
				responseType);

		Page<Pret> prets = result.getBody();

		return prets;
	}

	@Override
	public void creerPret(Pret pret,String mail,String motDePasse){

		RestTemplate rt = new RestTemplate();

		final String uri = apiUrl + "/prets?mail="+mail;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBasicAuth(mail, motDePasse);

		rt.exchange(uri, HttpMethod.POST, new HttpEntity<>(pret, headers), Pret.class);

	}

@Override
public void modifierUnPret(Long Id,String mail,String motDePasse) {
	RestTemplate rt = new RestTemplate();

	final String uri = apiUrl + "/prets/{id}";
	

	HttpHeaders headers = new HttpHeaders();
	headers.setContentType(MediaType.APPLICATION_JSON);
	headers.setBasicAuth(mail, motDePasse);

	rt.exchange(uri, HttpMethod.PUT, new HttpEntity<>( headers), Pret.class);

}
}
