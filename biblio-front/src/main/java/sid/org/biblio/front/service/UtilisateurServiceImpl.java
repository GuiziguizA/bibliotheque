package sid.org.biblio.front.service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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

import sid.org.biblio.front.classe.Livre;
import sid.org.biblio.front.classe.Sessions;
import sid.org.biblio.front.classe.Utilisateur;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

	
	@Autowired
	private HttpService httpService;
	
	@Value("${api.url}")
	private String apiUrl;

	@Override
	public void creerUtilisateur(Utilisateur utilisateur) throws HttpStatusCodeException {

		final String uri = apiUrl + "/users";
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers=new HttpHeaders();
	
	
		 rt.exchange(uri, HttpMethod.POST, new HttpEntity<>(utilisateur, headers),
					Utilisateur.class);
		

	}
	
	@Override
	public String identification(HttpServletRequest request) throws HttpStatusCodeException {

		final String uri = apiUrl + "/users/identity";
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers=new HttpHeaders();
		HttpSession session = request.getSession();
		Sessions sessions =new Sessions();
		sessions.setMail((String) session.getAttribute( "mail" ));
		sessions.setMotDePasse((String) session.getAttribute( "motDePasse" ));
	try {
		 rt.exchange(uri, HttpMethod.POST, new HttpEntity<>(sessions, headers),
					Sessions.class);
		 return "True";
		
	} catch (HttpStatusCodeException e) {
		return "False";
	}
		

	}
	
	@Override
	public Utilisateur infosUtilisateur(String mail,String motDePasse) {
		final String uri = apiUrl + "/user?mail="+mail;
	
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers =httpService.creerHeadersHttpAuthentifie(mail, motDePasse);
		
		try {
		ResponseEntity<Utilisateur> user = 	 rt.exchange(uri, HttpMethod.GET, new HttpEntity<>( headers),
						Utilisateur.class);
		return user.getBody();
		} catch (HttpStatusCodeException e) {
		return null;
		}
		
		
		
		
	}

}
