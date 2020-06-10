package sid.org.biblio.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import sid.org.biblio.front.classe.Livre;
import sid.org.biblio.front.classe.LivreCriteria;
import sid.org.biblio.front.classe.Pret;
import sid.org.biblio.front.classe.Utilisateur;
import sid.org.biblio.front.service.BookService;
import sid.org.biblio.front.service.PretService;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private BookService bookService;
	@Autowired
	private PretService pretService;

	@Override
	public void run(String... args) throws Exception {

		LivreCriteria critere = new LivreCriteria();
		critere.setNom("le");

		
		/* bookService.listLivre(critere, 1,1); */
			LivreCriteria criteres=new LivreCriteria();
        	criteres.setNom("le");
		 HttpHeaders headers = new HttpHeaders();
		 headers.setContentType(MediaType.APPLICATION_JSON);
		  HttpEntity<LivreCriteria> entity = new HttpEntity<>(criteres,headers);
		  System.out.println(entity.getBody());
		  		 Pret pret=new Pret();
		  		 Long id= (long) 2;
		  		 pret.setId(id);
		  		 
		  		 pretService.creerPret(pret);
		  		 
		/*
		 * pretService.pretsUtilisateur("gualisse@gmail.com", 1, 1);
		 */	}
}
