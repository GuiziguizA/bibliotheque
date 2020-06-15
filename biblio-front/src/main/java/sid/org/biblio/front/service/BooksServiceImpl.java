package sid.org.biblio.front.service;

import java.io.IOException;

import java.net.URL;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

	@Override
	public Livre livre(String id) throws Exception {

		RestTemplate rt = new RestTemplate();
		final String uri = apiUrl + "/books/" + id;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBasicAuth("gualisse@gmail.com", "motDePasse1");
		
			ResponseEntity<Livre> livre = rt.exchange(uri, HttpMethod.GET, new HttpEntity<>(headers), Livre.class);
			Livre book = livre.getBody();
			return book;
	}

	@Override
	public void createLivre(Livre livre) throws Exception {

		RestTemplate rt = new RestTemplate();
		final String uri = apiUrl + "/books";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBasicAuth("gualisse@gmail.com", "motDePasse1");
		try {
			ResponseEntity<Livre> livres = rt.exchange(uri, HttpMethod.POST, new HttpEntity<>(livre, headers),
					Livre.class);
		} catch (Exception e) {
			throw new Exception("le livre n'a pas pu etre ajouté");
		}

	}

	@Override
	public Page<Livre> livresRecherche(Optional<String> type, Optional<String> recherche, int size, int page) {

		String page1 = Integer.toString(page);
		String size1 = Integer.toString(size);
		RestTemplate rt = requestFactory.getRestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBasicAuth("gualisse@gmail.com", "motDePasse1");
		ParameterizedTypeReference<RestReponsePage<Livre>> responseType = new ParameterizedTypeReference<RestReponsePage<Livre>>() {
		};
		final String uri = apiUrl + "/books?page=" + page1 + "&size=" + size1;
		LivreCriteria critere = critereImpl(type, recherche);
		ResponseEntity<RestReponsePage<Livre>> result = rt.exchange(uri, HttpMethod.GET,
				new HttpEntity<>(critere, headers), responseType);
		Page<Livre> bookPage = result.getBody();
		return bookPage;
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

}
