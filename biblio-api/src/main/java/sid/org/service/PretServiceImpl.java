package sid.org.service;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sid.org.classe.Pret;
import sid.org.classe.Utilisateur;
import sid.org.dao.PretRepository;

@Service
public class PretServiceImpl implements PretService{
	@Autowired
	private PretRepository pretRepository;
	@Autowired
	private DateService dateService;
	
	@Override
	public Pret creerPret(Pret pret) throws Exception {

		if(pret.getLivre().getNombreExemplaire()<1) {
			throw new Exception("ce livre n'est pas disponible");
		}
		Date date1=new Date();
		
		

		pret.setDateDeDebut(date1);
		pret.setDateDeFin(dateService.modifierDate(date1, 2 ));
		
		return pretRepository.save(pret);
	}

	@Override
	public Pret modifierPret(Long id) throws Exception {
		Optional<Pret> pret=pretRepository.findById(id);
		
		if(!pret.isPresent()) {
			throw new Exception ("ce pret n'existe pas");
		}
		
		pret.get().setStatut("2 periode");
		
		
		return pretRepository.save(pret.get());
	}

	@Override
	public void supprimerPret(Long id) throws Exception {
		Optional<Pret> pret=pretRepository.findById(id);
		
		if(!pret.isPresent()) {
			throw new Exception ("ce pret n'existe pas");
		}
		
		pretRepository.delete(pret.get());
	}

	
	
	
	
	
	@Override
	public List<Pret> afficherPrets(Utilisateur utilisateur) throws Exception {
		List<Pret>listPretUtilisateur=pretRepository.findByUtilisateur(utilisateur);
		if(listPretUtilisateur.isEmpty()) {
			throw new Exception("Pas de pret en cours pour cette utilisateur");
		}
		
		return listPretUtilisateur;
	}
	
	@Override
	public   Map< String, Object> afficherPret(Long id) throws Exception {
		Optional<Pret> pret=pretRepository.findById(id);
		
		if(!pret.isPresent()) {
			throw new Exception ("ce pret n'existe pas");
		}
		
		  Map< String, Object> prets=new HashMap<>();
			
			prets.put("livre",pret);
	
	
			return prets;
		
	}

	@Override
	public Pret afficherUnPret(Long id)throws Exception{
		Optional<Pret> pret=pretRepository.findById(id);
		if(!pret.isPresent()) {
			throw new Exception ("ce pret n'existe pas");
		}
		return pret.get();
	}

}
