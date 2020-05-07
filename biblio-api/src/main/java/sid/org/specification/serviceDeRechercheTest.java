package sid.org.specification;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import sid.org.classe.Livre;
import sid.org.dao.LivreRepository;

public class serviceDeRechercheTest {
@Autowired
private LivreRepository livreRepository;

	public void givenLast__whenGettingListOfUsers__thenCorrect() {
	LivreSpecification spec =
	      new LivreSpecification();
	
	
	
	List<Livre> results = livreRepository.findAll(spec);
	
	}

}
