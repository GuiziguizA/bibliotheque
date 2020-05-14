package sid.org.service;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import sid.org.classe.Livre;
import sid.org.dao.LivreRepository;
import sid.org.specification.LivreSpecificationImpl;
import sid.org.specification.SearchCriteria;
import springfox.documentation.builders.RequestHandlerSelectors;



@Service
public class LivreServiceImpl implements LivreService{
	@Autowired
	private LivreRepository livreRepository;
	
	

	@Override
	public Livre createLivre(Livre livre) throws Exception {
		List<Livre>livreNom=livreRepository.findByNom(livre.getNom());
		List<Livre>livreAuteur=livreRepository.findByAuteur(livre.getAuteur());
		
		if(!livreNom.isEmpty() && !livreAuteur.isEmpty() ) {
			throw new Exception();
		}
		
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
	
	public Livre modificationNombreExemplaire(Long id,@Nullable int nombre) throws Exception {
		
	if(nombre==(Integer)null) {
		nombre=1;
	}
		
		
		Optional<Livre> livre=livreRepository.findById(id);
		if (!livre.isPresent()) {
			throw new Exception("Le livre n'existe pas");
		}
		if(livre.get().getNombreExemplaire()<1) {
			throw new Exception("Le livre n'est pas disponible");
		}
		
		livre.get().setNombreExemplaire(livre.get().getNombreExemplaire()-nombre);
	
	
		return 	livreRepository.saveAndFlush(livre.get());
	}
@Override
	public Map<String, Object> rechercherLivres(String recherche) throws Exception {
		LivreSpecificationImpl spec = new LivreSpecificationImpl(new SearchCriteria("nom", recherche));
		
		
		
		List<Livre> results = livreRepository.findAll(spec);
		if (results.isEmpty()) {
			throw new Exception("Aucun resultats");
		}
		
		  Map< String, Object> livres=new HashMap<>();
			
			livres.put("results",results);
		
		return livres;
	}

@Override
public Page<Livre> searchLivres(String recherche) throws Exception {
	LivreSpecificationImpl spec = new LivreSpecificationImpl(new SearchCriteria("nom",  recherche));
	
	Pageable pageable=PageRequest.of(0, 2);
	
	Page<Livre> results = livreRepository.findAll(spec,pageable);
	if (results.isEmpty()) {
		throw new Exception("Aucun resultats");
	}
	
	
	
	return results;
}

}
