package sid.org.biblio.front.service;
import java.io.IOException;

import java.net.URL;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import sid.org.biblio.front.classe.Livre;



@Service
public class BooksServiceImpl implements BookService{
@Override
	public Livre livre(String id){
		
		ObjectMapper mapper = new ObjectMapper ();
		Livre books;
		try {
			books=mapper.readValue(new URL("http://localhost:8081/books"+id), Livre.class);
			return books;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
	
}
