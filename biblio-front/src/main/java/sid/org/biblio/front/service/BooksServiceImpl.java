package sid.org.biblio.front.service;
import java.io.IOException;

import java.net.URL;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import sid.org.biblio.front.classe.Livre;



@Service
public class BooksServiceImpl implements BookService{
@Override
	public Livre livre(String id){
		
		RestTemplate rt = new RestTemplate();
		final String uri = "http://localhost:8081/books/1";
		ResponseEntity<Livre> livre = rt.getForEntity(uri, Livre.class);
		Livre book = livre.getBody();
		
		return book;
	}
@Override
public void createLivre(Livre livre){
	
	RestTemplate rt = new RestTemplate();
	final String uri = "http://localhost:8081/books";
	 
	ResponseEntity<Livre> livres = rt.postForEntity(uri, livre, Livre.class);
	
	System.out.println(livres);
	
}
}
