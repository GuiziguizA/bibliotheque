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
	@Override
	@Transactional
	public Pret creerPret(PretDto pretDto,Principal principal) throws BibliothequeException {
		Optional<Utilisateur> utilisateur = utilisateurRepository.findByMail(principal.getName());
		Optional<Livre>livre=livreRepository.findByCodeLivre(pretDto.getLivre().getCodeLivre());
		if(livre.get().getNombreExemplaire()< pretDto.getLivre().getNombreExemplaire()) {
			throw new LivreIndisponibleException("ce livre n'est pas disponible");
		}
		if(livre.get().getNombreExemplaire()< pretDto.getLivre().getNombreExemplaire()) {
			throw new LivreIndisponibleException("ce livre n'est pas disponible pour "+pretDto.getLivre().getNombreExemplaire()+" exemplaires");
		}
		
		
		Date date1=new Date();
		
		Pret pret = convertToPret(pretDto);

		pret.setDateDeDebut(date1);
		pret.setDateDeFin(dateService.modifierDate(date1, 2 ));
		pret.setUtilisateur(utilisateur.get());
		pret.setNombreLivres(pretDto.getNombreLivres());
		livre.get().setNombreExemplaire(livre.get().getNombreExemplaire()-pretDto.getLivre().getNombreExemplaire());
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
		livre.get().setNombreExemplaire(livre.get().getNombreExemplaire()+pret.get().getNombreLivres());
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

	private Pret convertToPret(PretDto pretDto) {
		Pret pret = new Pret();
		pret.setLivre(pretDto.getLivre());
		pret.setNombreLivres(pretDto.getNombreLivres());
		return pret;
		
	}
	
	@Override
	 public void modifierStatut(Long id) throws ResultNotFoundException {
	 	 Date aujourdhui = new Date(); 
	 	 Optional<Pret> pret =pretRepository.findById(id);
	 	 if(pret.get().getDateDeFin().compareTo(aujourdhui)>0 && pret.get().getStatut()=="deuxiemeTemps") {
	 		 pret.get().setStatut("depasse");
	 		 pretRepository.saveAndFlush(pret.get());
	 	 }else if (pret.get().getDateDeFin().compareTo(aujourdhui)>0 && pret.get().getStatut()=="premierTemps") {
	 		 pret.get().setStatut("deuxiemeTemps");
	 		pret.get().setDateDeFin( dateService.modifierDate(pret.get().getDateDeFin(), 2));
	 		 pretRepository.saveAndFlush(pret.get());
	 	 }
	 }
	
	
	 
@Override
public void modifierStatutsPrets() throws ResultNotFoundException {
	
	ArrayList<Pret>prets=(ArrayList<Pret>)afficherPrets();
	for(int i = 0; i <prets.size(); i++) {
		modifierStatut(prets.get(i).getCodePret());
	}
	
	}

	





	
}
