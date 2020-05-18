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
import sid.org.exception.BibliothequeException;
import sid.org.exception.DemandeUtilisateurIncorrectException;
import sid.org.exception.EntityAlreadyExistException;

import sid.org.exception.LivreIndisponibleException;
import sid.org.exception.ResultNotFoundException;
import sid.org.specification.LivreSpecificationImpl;
import sid.org.specification.SearchCriteria;
import springfox.documentation.builders.RequestHandlerSelectors;



@Service
public class LivreServiceImpl implements LivreService{
	@Autowired
	private LivreRepository livreRepository;
	
	

	@Override
	public Livre createLivre(Livre livre) throws EntityAlreadyExistException {
		List<Livre>livreNom=livreRepository.findByNom(livre.getNom());
		List<Livre>livreAuteur=livreRepository.findByAuteur(livre.getAuteur());
		
		if(!livreNom.isEmpty() && !livreAuteur.isEmpty() ) {
			throw new  EntityAlreadyExistException();
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
	public Map<String, Object> afficheUnLivre( Long id) throws DemandeUtilisateurIncorrectException {
		Optional<Livre> book=livreRepository.findByCodeLivre(id);
		if(!book.isPresent()) {
			throw new DemandeUtilisateurIncorrectException("le livre existe pas");
		}
		
		  
		  Map< String, Object> livre=new HashMap<>();
			
			livre.put("livre",book.get());
	
	
			return livre;
		}


  
	@Override
	
	public Livre modificationNombreExemplaire(Long id,@Nullable int nombre) throws BibliothequeException {
		
	if(nombre==(Integer)null) {
		nombre=1;
	}
		
		
		Optional<Livre> livre=livreRepository.findById(id);
		if (!livre.isPresent()) {
			throw new DemandeUtilisateurIncorrectException("Le livre n'existe pas");
		}
		if(livre.get().getNombreExemplaire()<1) {
			throw new LivreIndisponibleException("Le livre n'est pas disponible");
		}
		
		livre.get().setNombreExemplaire(livre.get().getNombreExemplaire()-nombre);
	
	
		return 	livreRepository.saveAndFlush(livre.get());
	}
	/*
	 * @Override public Map<String, Object> rechercherLivres(String recherche)
	 * throws ResultNotFoundException{ LivreSpecificationImpl spec = new
	 * LivreSpecificationImpl(new SearchCriteria("nom", recherche));
	 * 
	 * 
	 * 
	 * List<Livre> results = livreRepository.findAll(spec); if (results.isEmpty()) {
	 * throw new ResultNotFoundException("Aucun resultats"); }
	 * 
	 * Map< String, Object> livres=new HashMap<>();
	 * 
	 * livres.put("results",results);
	 * 
	 * return livres; }
	 */

@Override
public Page<Livre> searchLivres(String recherche) {
	LivreSpecificationImpl spec = new LivreSpecificationImpl(new SearchCriteria("nom",  recherche));
	
	Pageable pageable=PageRequest.of(0, 2);
	
	Page<Livre> results = livreRepository.findAll(spec,pageable);
	
	
	return results;
}

}
