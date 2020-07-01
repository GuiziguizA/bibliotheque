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
import sid.org.dto.LivreDto;
import sid.org.exception.BibliothequeException;

import sid.org.exception.EntityAlreadyExistException;

import sid.org.exception.LivreIndisponibleException;
import sid.org.exception.ResultNotFoundException;
import sid.org.specification.LivreCriteria;
import sid.org.specification.LivreSpecificationImpl;





@Service
public class LivreServiceImpl implements LivreService{
	@Autowired
	private LivreRepository livreRepository;
	
	

	/*
	 * Creation d'un Livre
	 * @param LivreDto 
	 * 
	 * @return Livre
	 */
	
	@Override
	public Livre createLivre(LivreDto livreDto) throws EntityAlreadyExistException {
		List<Livre>livreNom=livreRepository.findByNom(livreDto.getNom());
		List<Livre>livreAuteur=livreRepository.findByAuteur(livreDto.getAuteur());
		
		if(!livreNom.isEmpty() && !livreAuteur.isEmpty() ) {
			throw new  EntityAlreadyExistException();
		}
		
		Livre livre= convertoToEntity(livreDto);
		
		return 	livreRepository.save(livre);
	}

	/*
	 * Suppression d'un Livre
	 * @param Long id  
	 * 
	 * 
	 */
@Override
	public void supprimerLivre(Long id)throws ResultNotFoundException{
		
		Optional<Livre>livre=livreRepository.findById(id);
		if(!livre.isPresent()) {
			throw new ResultNotFoundException();
		}
		livreRepository.delete(livre.get());
	}
	
/*
 *Affichage  d'un Livre
 * @param L 
 * 
 * @return Livre
 */
	
	@Override
	public Livre  afficheUnLivre( Long id) throws ResultNotFoundException{
		Optional<Livre> book=livreRepository.findByCodeLivre(id);
		if(!book.isPresent()) {
			throw new ResultNotFoundException("le livre existe pas");
		}
		
		 
	
			return book.get();
		}


	/*
	 *Rechercher des livre en fonction d'un critere
	 * @param LivreCriteria livreCriteria 
	 * @param int page 
	 * @param int size
	 * @return Page<Livre>
	 */


@Override
public Page<Livre> searchLivres(LivreCriteria livreCriteria,int page ,int size) throws ResultNotFoundException {
	LivreSpecificationImpl spec = new LivreSpecificationImpl(livreCriteria);
	
	if(size==0) {
		throw new ResultNotFoundException();
	}
	
	Pageable pageable=PageRequest.of(page,size );
	
	Page<Livre> results = livreRepository.findAll(spec,pageable);
	
	
	return results;
}


/*
 *Convertir un LivreDto en Livre
 * @param LivreDto livreDto 
 * 
 * 
 * @return Livre
 */

private Livre convertoToEntity(LivreDto livreDto) {
	
	Livre livre=new Livre();
	
	livre.setAuteur(livreDto.getAuteur());
	livre.setEmplacement(livreDto.getEmplacement());
	livre.setNom(livreDto.getNom());
	livre.setNombreExemplaire(livreDto.getNombreExemplaire());
	livre.setSection(livreDto.getSection());
	livre.setType(livreDto.getType());
	return livre;
	
}

}
