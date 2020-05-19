package sid.org.service;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sid.org.classe.Pret;
import sid.org.classe.Utilisateur;
import sid.org.dao.PretRepository;
import sid.org.exception.BibliothequeException;
import sid.org.exception.DemandeUtilisateurIncorrectException;
import sid.org.exception.LivreIndisponibleException;
import sid.org.exception.MauvaiseDemandeException;

@Service
public class PretServiceImpl implements PretService{
	@Autowired
	private PretRepository pretRepository;
	@Autowired
	private DateService dateService;
	
	@Override
	public Pret creerPret(Pret pret) throws BibliothequeException {

		if(pret.getLivre().getNombreExemplaire()<1) {
			throw new LivreIndisponibleException("ce livre n'est pas disponible");
		}
		Date date1=new Date();
		
		

		pret.setDateDeDebut(date1);
		pret.setDateDeFin(dateService.modifierDate(date1, 2 ));
		
		return pretRepository.save(pret);
	}

	@Override
	public Pret modifierPret(Long id) throws DemandeUtilisateurIncorrectException {
		Optional<Pret> pret=pretRepository.findById(id);
		
		if(!pret.isPresent()) {
			throw new DemandeUtilisateurIncorrectException ("ce pret n'existe pas");
		}
		
		pret.get().setStatut("2 periode");
		
		
		return pretRepository.save(pret.get());
	}

	@Override
	public void supprimerPret(Long id) throws DemandeUtilisateurIncorrectException {
		Optional<Pret> pret=pretRepository.findById(id);
		
		if(!pret.isPresent()) {
			throw new DemandeUtilisateurIncorrectException ("ce pret n'existe pas");
		}
		
		pretRepository.delete(pret.get());
	}

	
	
	
	
	
	@Override
	public Page<Pret> afficherPrets(Utilisateur utilisateur,int page,int size) throws MauvaiseDemandeException  {
		if(size==0) {
			throw new MauvaiseDemandeException();
		}
		Pageable pageable=PageRequest.of(page, size);
		Page<Pret>listPretUtilisateur=pretRepository.findByUtilisateur(utilisateur,pageable);

		
		return listPretUtilisateur;
	}
	
	@Override
	public   Pret afficherPret(Long id) throws DemandeUtilisateurIncorrectException {
		Optional<Pret> pret=pretRepository.findById(id);
		
	if (!pret.isPresent()) {
		throw new DemandeUtilisateurIncorrectException("le pret n'existe pas");
	}
	
	
			return pret.get();
		
	}
	
	@Override
	public  List<Pret> afficherPrets() throws DemandeUtilisateurIncorrectException {
		List<Pret> prets=pretRepository.findAll();
		

	
			return prets;
		
	}

	@Override
	public  List<Pret> afficherPrets(String statut) throws DemandeUtilisateurIncorrectException {
		List<Pret> prets=pretRepository.findByStatut(statut);
		

	
			return prets;
		
	}
	
	@Override
	public Pret afficherUnPret(Long id)throws DemandeUtilisateurIncorrectException{
		Optional<Pret> pret=pretRepository.findById(id);
		if(!pret.isPresent()) {
			throw new DemandeUtilisateurIncorrectException ("ce pret n'existe pas");
		}
		return pret.get();
	}

}
