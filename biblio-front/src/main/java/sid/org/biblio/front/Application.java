package sid.org.biblio.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.client.RestTemplate;

import sid.org.biblio.front.classe.LivreCriteria;
import sid.org.biblio.front.classe.Pret;
import sid.org.biblio.front.classe.Sessions;
import sid.org.biblio.front.classe.Utilisateur;
import sid.org.biblio.front.config.SimpleAuthenticationFilter;
import sid.org.biblio.front.config.UserDetailsImpl;
import sid.org.biblio.front.service.BookService;
import sid.org.biblio.front.service.PretService;
import sid.org.biblio.front.service.UtilisateurService;

@SpringBootApplication
/* @ImportResource("classpath:spring-security.xml") */
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private BookService bookService;
	@Autowired
	private PretService pretService;
	@Autowired
	private UserDetailsImpl userDetailsImpl;

	@Override
	public void run(String... args) throws Exception {

		/*
		 * LivreCriteria critere = new LivreCriteria(); critere.setNom("le");
		 * 
		 * bookService.listLivre(critere, 1,1); LivreCriteria criteres = new
		 * LivreCriteria(); criteres.setNom("le"); HttpHeaders headers = new
		 * HttpHeaders(); headers.setContentType(MediaType.APPLICATION_JSON);
		 * HttpEntity<LivreCriteria> entity = new HttpEntity<>(criteres, headers);
		 * System.out.println(entity.getBody()); Pret pret = new Pret(); Long id =
		 * (long) 2; pret.setId(id);
		 */
		RestTemplate rt = new RestTemplate();
		 String uri ="http://localhost:8081/users/identity";
		 String username = "gualisse@gmail.com";
		    String motDePasse = "motDePasse1";
		    Sessions sessions=new Sessions();
	    sessions.setMail(username);
	    sessions.setMotDePasse(motDePasse);
		ResponseEntity<Utilisateur> user= rt.exchange(uri,HttpMethod.POST,new HttpEntity<>(sessions),Utilisateur.class);
		Utilisateur utilisateur = user.getBody();
		System.out.println(utilisateur.getMail());
		
		/*
		 * pretService.pretsUtilisateur("gualisse@gmail.com", 1, 1);
		 */ }
}
