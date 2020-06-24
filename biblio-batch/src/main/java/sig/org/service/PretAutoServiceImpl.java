package sig.org.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import sig.org.classe.Pret;



@Service
public class PretAutoServiceImpl implements PretAutoService{

	@Autowired
	private EmailService emailService;

	

	@Value("${api.url}")
	private String apiUrl;
	@Value("${spring.api.identifiant}")
	private String identifiant;
	@Value("${spring.api.motDePasse}")
	private String motDePasse;
	
	@Value("${mail.bibliotheque}")
	private String biblioMail;
	@Value("${mail.subject}")
	private String subject;
	@Value("${mail.message}")
	private String message;
	
	
	
	
	@Override
public void envoieMails() throws Exception {
		RestTemplate rt = new RestTemplate();
		final String uri = apiUrl + "/listprets?statut=depasse";
		
	
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBasicAuth(identifiant, motDePasse);
		ParameterizedTypeReference<List<Pret>> responseType = new ParameterizedTypeReference<List<Pret>>() {
		};
		ResponseEntity<List<Pret>> pret = rt.exchange(uri, HttpMethod.GET, new HttpEntity<>(headers), responseType);
		List<Pret>prets= pret.getBody();
		if (!prets.isEmpty()) {
			for(int i = 0; i < prets.size(); i++) {
				emailService.sendMail(biblioMail,prets.get(i).getUtilisateur().getMail(),subject,message+prets.get(i).getLivre().getNom());
				
			}
		}
		
		
		
}
	
	@Override
public void modifierStatutPrets() {
	
	RestTemplate rt = new RestTemplate();
	final String uri = apiUrl + "/prets/statuts";
	

	
	HttpHeaders headers = new HttpHeaders();
	headers.setContentType(MediaType.APPLICATION_JSON);
	headers.setBasicAuth(identifiant, motDePasse);
	rt.postForLocation(uri,new HttpEntity<>(headers));
	
	
}
	
	
}