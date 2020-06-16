package sid.org.biblio.front.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import sid.org.biblio.front.classe.Utilisateur;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

	@Value("${api.url}")
	private String apiUrl;
	@Value("${spring.api.identifiant}")
	private String identifiant;
	@Value("${spring.api.motDePasse}")
	private String motDePasse;
	@Override
	public void creerUtilisateur(Utilisateur utilisateur) throws HttpStatusCodeException {

		final String uri = apiUrl + "/users";
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBasicAuth(identifiant, motDePasse);
		try {
			ResponseEntity<Utilisateur> user = rt.exchange(uri, HttpMethod.POST, new HttpEntity<>(utilisateur),
					Utilisateur.class);
		} catch (HttpStatusCodeException e) {
			throw e;
		}

	}

}
