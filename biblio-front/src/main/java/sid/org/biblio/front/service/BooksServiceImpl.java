package sid.org.biblio.front.service;
import java.io.IOException;

import java.net.URL;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import sid.org.biblio.front.classe.Livre;
import sid.org.biblio.front.classe.LivreCriteria;
import sid.org.biblio.front.classe.Pret;



@Service
public class BooksServiceImpl implements BookService{
@Override
	public Livre livre(String id) throws Exception{
		
		RestTemplate rt = new RestTemplate();
		final String uri = "http://localhost:8081/books/"+id;
		try{
			ResponseEntity<Livre> livre = rt.getForEntity(uri, Livre.class);
			Livre book = livre.getBody();
			
			return book;
		} catch(HttpStatusCodeException e){
		     String errorpayload = e.getResponseBodyAsString();
		     //do whatever you want
		} catch(RestClientException e){
		     //no response payload, tell the user sth else 
		}
		return null;
		
		
	
	}
@Override
public void createLivre(Livre livre) throws Exception{
	
	RestTemplate rt = new RestTemplate();
	final String uri = "http://localhost:8081/books";
	 try {
		 ResponseEntity<Livre> livres = rt.postForEntity(uri, livre, Livre.class);
	} catch (Exception e) {
		throw new Exception("le livre n'a pas pu etre ajouté");
	}
	
	

	
	
}

@Override
public Page<Livre>   listLivre(LivreCriteria livreCriteria,int size,int page) throws Exception {
	
	RestTemplate rt = new RestTemplate();
	String page1 = Integer.toString(page); 
    String size1 = Integer.toString(size); 
	final String uri = "http://localhost:8081/books?page="+page1+"&size="+size1;
	
	
	
	HttpEntity<LivreCriteria>critere=new HttpEntity<LivreCriteria>(livreCriteria);
	
	ParameterizedTypeReference<RestReponsePage<Livre>> responseType = new ParameterizedTypeReference<RestReponsePage<Livre>>() { };

	ResponseEntity<RestReponsePage<Livre>> result = rt.exchange(uri, HttpMethod.GET, critere, responseType);
	
	Page<Livre>livres=result.getBody();
	return  livres;

}
}
