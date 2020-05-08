package sid.org.specification;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sid.org.classe.Livre;
import sid.org.classe.SearchCriteria;
import sid.org.dao.LivreRepository;
import sid.org.metier.LivreSpecification;
@Service
public class serviceDeRechercheTest {
@Autowired
private LivreRepository livreRepository;


	public Map<String, Object> rechercherLivres(String recherche) throws Exception {
	LivreSpecification spec = new LivreSpecification(new SearchCriteria("nom", ":", recherche));
	
	
	
	List<Livre> results = livreRepository.findAll(spec);
	if (results.isEmpty()) {
		throw new Exception("Aucun resultats");
	}
	
	  Map< String, Object> livres=new HashMap<>();
		
		livres.put("livre",results);
	
	return livres;
	
	}

}
