package sid.org.biblio.front.service;

import java.io.IOException;

import java.net.URL;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.Nullable;

import groovy.util.logging.Slf4j;
import sid.org.biblio.front.classe.Livre;
import sid.org.biblio.front.classe.LivreCriteria;
import sid.org.biblio.front.config.RequestFactory;
import sid.org.biblio.front.config.SimpleAuthenticationFilter;
import sid.org.biblio.front.enumeration.ListType;
import sid.org.biblio.front.enumeration.Types;

@Component
@Slf4j
public class BooksServiceImpl implements BookService {

	private final RequestFactory requestFactory;

	@Autowired
	public BooksServiceImpl(RequestFactory requestFactory) {
		this.requestFactory = requestFactory;
	}

	
	@Value("${api.url}")
	private String apiUrl;
	
	@Value("${spring.api.identifiant}")
	private String identifiant;
	@Value("${spring.api.motDePasse}")
	private String motDePasse;
	
	@Override
	public Livre livre(String id,String mail,String motDePasse) throws HttpStatusCodeException {

		RestTemplate rt = new RestTemplate();
		final String uri = apiUrl + "/books/" + id;
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBasicAuth(mail, motDePasse);
		try {
			ResponseEntity<Livre> livre = rt.exchange(uri, HttpMethod.GET, new HttpEntity<>(headers), Livre.class);
			Livre book = livre.getBody();
			return book;
		} catch (HttpStatusCodeException e) {
			throw e;
			}
	}

	@Override
	public void createLivre(Livre livre,String mail,String motDePasse) throws HttpStatusCodeException {

		RestTemplate rt = new RestTemplate();
		final String uri = apiUrl + "/books";
		
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBasicAuth(mail, motDePasse);
		try {
			ResponseEntity<Livre> livres = rt.exchange(uri, HttpMethod.POST, new HttpEntity<>(livre, headers),
					Livre.class);
		} catch (HttpStatusCodeException e) {
		throw e;
		}

	}

	@Override
	public Page<Livre> livresRecherche(Optional<String> type, Optional<String> recherche, int size, int page,String mail,String motDePasse)throws HttpStatusCodeException {

		String page1 = Integer.toString(page);
		String size1 = Integer.toString(size);
		RestTemplate rt = requestFactory.getRestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBasicAuth(mail, motDePasse);
		ParameterizedTypeReference<RestReponsePage<Livre>> responseType = new ParameterizedTypeReference<RestReponsePage<Livre>>() {
		};
		final String uri = apiUrl + "/books?page=" + page1 + "&size=" + size1;
		LivreCriteria critere = critereImpl(type, recherche);
		try {
			ResponseEntity<RestReponsePage<Livre>> result = rt.exchange(uri, HttpMethod.GET,
					new HttpEntity<>(critere, headers), responseType);
			Page<Livre> bookPage = result.getBody();
			return bookPage;
		} catch (HttpStatusCodeException e) {
			throw e;
		}
		
	
	
	}

	public LivreCriteria critereImpl(Optional<String> type, Optional<String> recherche) {
		LivreCriteria critere = new LivreCriteria();

		if (type.get().equals("nom") && recherche != null) {
			critere.setNom(recherche.get());
		}
		if (type.get().equals("auteur") && recherche != null) {
			critere.setAuteur(recherche.get());
		}
		if (type.get().equals("type") && recherche != null) {
			critere.setType(recherche.get());
		}

		return critere;

	}
	@Override
	public List<Types> chargerLesTypesDeRecherches(){
		
		ListType listType=new ListType();
		List<Types>types=listType.listTypes();
		
		return types;
		
	}
	
	@Override
	public void supprimerUnLivre(Long id,String mail,String motDePasse) throws HttpStatusCodeException{
		RestTemplate rt = new RestTemplate();
		final String uri = apiUrl + "/books?id="+id;
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBasicAuth(mail, motDePasse);
		try {
			rt.exchange(uri, HttpMethod.DELETE, new HttpEntity<>(headers),Long.class);
		} catch (HttpStatusCodeException e) {
			throw e;
		}
		
	}
}
