package sid.org.service;


import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sid.org.classe.Livre;
import sid.org.classe.Pret;
import sid.org.classe.Utilisateur;
import sid.org.dao.LivreRepository;
import sid.org.dao.PretRepository;
import sid.org.dao.UtilisateurRepository;
import sid.org.dto.PretDto;
import sid.org.exception.BibliothequeException;
import sid.org.exception.EntityAlreadyExistException;
import sid.org.exception.LivreIndisponibleException;
import sid.org.exception.ResultNotFoundException;

@Service
public class PretServiceImpl implements PretService{
	@Autowired
	private PretRepository pretRepository;
	@Autowired
	private DateService dateService;
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
	private LivreRepository livreRepository;
	
	@Value("${pret.statut1}")
	private String statut1;
	 @Value("${pret.statut2}")
	 private String statut2;
	 @Value("${pret.statut3}")
	 private String statut3;
	 @Value("${pret.statut4}")
		private String statut4;
	 
	@Override
	@Transactional
	public Pret creerPret(Long idLivre,String mail) throws ResultNotFoundException,LivreIndisponibleException, EntityAlreadyExistException {
		
		Optional<Utilisateur> utilisateur = utilisateurRepository.findByMail(mail);
		Optional<Livre>livre=livreRepository.findByCodeLivre(idLivre);
		
		if(!utilisateur.isPresent()) {
			throw new ResultNotFoundException("l'utilisateur n'existe pas");
		}
		if(!livre.isPresent()) {
			throw new ResultNotFoundException("le livre n'existe pas");
		}

		if(livre.get().getNombreExemplaire()< 1) {
			throw new LivreIndisponibleException("ce livre n'est pas disponible pour "+livre.get().getNombreExemplaire()+" exemplaires");
		}
		
		Optional<Pret>pret1=pretRepository.findByUtilisateurAndLivre(utilisateur.get(),livre.get());
		if(pret1.isPresent()&&!pret1.get().getStatut().equals(statut4)) {
			throw new EntityAlreadyExistException("La reservation existe deja pour ce livre");
		}
		
		
		Date date1=new Date();
		
		Pret pret = new Pret();
		pret.setLivre(livre.get());
		pret.setDateDeDebut(date1);
		pret.setDateDeFin(dateService.modifierDate(date1, 2 ));
		pret.setUtilisateur(utilisateur.get());
		pret.setNombreLivres(1);
		pret.setStatut(statut1);
		livre.get().setNombreExemplaire(livre.get().getNombreExemplaire()-1);
		livreRepository.saveAndFlush(livre.get());
		return pretRepository.saveAndFlush(pret);
	}

	
	@Transactional
	@Override
	public void supprimerPret(Long id) throws ResultNotFoundException {
		Optional<Pret> pret=pretRepository.findById(id);
		
		if(!pret.isPresent()) {
			throw new ResultNotFoundException("ce pret n'existe pas");
		}
		Optional<Livre> livre=livreRepository.findById(pret.get().getLivre().getCodeLivre());
		if(!pret.get().getStatut().equals(statut4)) {
		livre.get().setNombreExemplaire(livre.get().getNombreExemplaire()+pret.get().getNombreLivres());}
		livreRepository.saveAndFlush(livre.get());
		pretRepository.delete(pret.get());
	
	}

	
	
	
	
	
	@Override
	public Page<Pret> afficherPrets(String mail,int page,int size) throws ResultNotFoundException  {
		if(size==0) {
			throw new ResultNotFoundException();
		}
		Optional<Utilisateur> utilisateur=utilisateurRepository.findByMail(mail);
		if(!utilisateur.isPresent()) {
			throw new ResultNotFoundException("l'utilisateur est introuvable");
		}
		
		Pageable pageable=PageRequest.of(page, size);
		Page<Pret>listPretUtilisateur=pretRepository.findByUtilisateur(utilisateur.get(),pageable);

		
		return listPretUtilisateur;
	}
	
	@Override
	public   Pret afficherPret(Long id) throws ResultNotFoundException{
		Optional<Pret> pret=pretRepository.findById(id);
		
	if (!pret.isPresent()) {
		throw new ResultNotFoundException("le pret n'existe pas");
	}
	
	
			return pret.get();
		
	}
	
	@Override
	public  List<Pret> afficherPrets() throws ResultNotFoundException{
		List<Pret> prets=pretRepository.findAll();
		

	
			return prets;
		
	}

	@Override
	public  List<Pret> afficherPrets(String statut) throws ResultNotFoundException{
		List<Pret> prets=pretRepository.findByStatut(statut);
		

	
			return prets;
		
	}
	
	@Override
	public Pret afficherUnPret(Long id)throws ResultNotFoundException{
		Optional<Pret> pret=pretRepository.findById(id);
		if(!pret.isPresent()) {
			throw new ResultNotFoundException("ce pret n'existe pas");
		}
		return pret.get();
	}


	 
	@Override
	 public void modifierStatut(Long id) throws ResultNotFoundException {
	 	 Date aujourdhui = new Date(); 
	 	 Optional<Pret> pret =pretRepository.findById(id);
	 	 if(pret.get().getDateDeFin().compareTo(aujourdhui)>0 && pret.get().getStatut()==statut2) {
	 		 pret.get().setStatut(statut3);
	 		 pretRepository.saveAndFlush(pret.get());
	 	 }else if (pret.get().getDateDeFin().compareTo(aujourdhui)>0 && pret.get().getStatut()==statut1) {
	 		 pret.get().setStatut(statut2);
	 		pret.get().setDateDeFin( dateService.modifierDate(pret.get().getDateDeFin(), 2));
	 		 pretRepository.saveAndFlush(pret.get());
	 	 }
	 }
	
	
	 
@Override
public void modifierStatutsPrets() throws ResultNotFoundException {
	
	ArrayList<Pret>prets=(ArrayList<Pret>)afficherPrets();
	if (prets.isEmpty()) {
	for(int i = 0; i <prets.size(); i++) {
		modifierStatut(prets.get(i).getId());
	}
	}
	}


@Override
public void modifierPret(Long id) throws ResultNotFoundException{
	Optional<Pret>pret=pretRepository.findById(id);
	
	if(!pret.isPresent()) {
		throw new ResultNotFoundException("Ce pret n'existe pas");
	}
	Optional<Livre>livre=livreRepository.findByCodeLivre(pret.get().getLivre().getCodeLivre());
	if(!livre.isPresent()) {
		throw new ResultNotFoundException("Ce livre n'existe pas");
	}
	pret.get().setStatut(statut4);
	livre.get().setNombreExemplaire(livre.get().getNombreExemplaire() + 1);
	pretRepository.saveAndFlush(pret.get());
	livreRepository.saveAndFlush(livre.get());
}




	
}
