package sid.org.metier;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import sid.org.classe.Livre;
import sid.org.dao.LivreRepository;
import sid.org.service.ILivre;
import sid.org.specification.LivreSpecification;
import sid.org.specification.SearchCriteria;



@Service
public class LivreService implements ILivre{
	@Autowired
	private LivreRepository livreRepository;
	
	

	@Override
	public Livre createLivre(Livre livre) {
		return 	livreRepository.save(livre);
	}

	

	/*
	 * @Override public EntityModel<Livre> afficheUnLivre( Long id) {
	 * 
	 * Optional<Livre> livre = livreRepository.findById(id);
	 * 
	 * return new EntityModel<>(livre.get(),
	 * linkTo(methodOn(LivreService.class).afficheUnLivre(id)).withSelfRel(),
	 * linkTo(methodOn(LivreService.class).afficherLivres(null,null)).withRel(
	 * "livres")); }
	 * 
	 */
	/*
	 * @Override public CollectionModel<EntityModel<Livre>> afficherLivres(String
	 * nom, String type) {
	 * 
	 * List<EntityModel<Livre>>livres = livreRepository.findByNomAndtype(nom,
	 * type).stream() .map(livre -> { return new EntityModel<>(livre,
	 * linkTo(methodOn(LivreController.class).afficheUnLivre(livre.getCodeLivre())).
	 * withSelfRel(), linkTo(methodOn(LivreController.class).afficherLivres( nom,
	 * type)).withRel("livres"));
	 * 
	 * }) .collect(Collectors.toList());
	 * 
	 * 
	 * return new CollectionModel<>(livres,
	 * linkTo(methodOn(LivreService.class).afficherLivres(nom,
	 * type)).withSelfRel()); }
	 * 
	 */
	
	
	
	@Override
	public Map<String, Object> afficheUnLivre( Long id) throws Exception {
		Optional<Livre> book=livreRepository.findByCodeLivre(id);
		if(!book.isPresent()) {
			throw new Exception("le livre existe pas");
		}
		
		  
		  Map< String, Object> livre=new HashMap<>();
			
			livre.put("livre",book.get());
	
	
			return livre;
		}


	@Override
	  public Map<String, Object> afficherLivres(@Nullable String nom, @Nullable String type)  {
			  

		 
		 ArrayList<Livre>listLivres=livreRepository.findByNomAndtype(nom, type);
		 
	
	
			  Map<String, Object> livres=new HashMap<>();
				
				livres.put("listLivres",listLivres );
					
			  
					  return livres;
		  }
		
		

  
  
  
  
	@Override
	public Livre modificationNombreExemplaire(Long id) throws Exception {
		
		Optional<Livre> livre=livreRepository.findById(id);
		if (!livre.isPresent()) {
			throw new Exception("Le livre n'existe pas");
		}
		if(livre.get().getNombreExemplaire()<1) {
			throw new Exception("Le livre n'est pas disponible");
		}
		
		livre.get().setNombreExemplaire(livre.get().getNombreExemplaire()-1);
	
	
		return 	livreRepository.save(livre.get());
	}

	
	
}
