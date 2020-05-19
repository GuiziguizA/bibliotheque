package sid.org.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sid.org.classe.Pret;
import sid.org.dao.PretRepository;
import sid.org.service.DateService;
import sid.org.service.PretService;
@Service
public class PretAutoServiceImpl implements PretAutoService{
	@Autowired
	private PretService pretService;
	@Autowired
	private MailService mailService;
	@Autowired
	private PretRepository pretRepository;
	@Autowired
	private DateService dateService;
	   
	@Override
		 public void modifierStatut(Long id) throws Exception {
		 	 Date aujourdhui = new Date(); 
		 	 Optional<Pret> pret =pretRepository.findById(id);
		 	 if(pret.get().getDateDeFin().compareTo(aujourdhui)>0 && pret.get().getStatut()=="deuxiemeTemps") {
		 		 pret.get().setStatut("depasse");
		 		 pretRepository.save(pret.get());
		 	 }else if (pret.get().getDateDeFin().compareTo(aujourdhui)>0 && pret.get().getStatut()=="premierTemps") {
		 		 pret.get().setStatut("deuxiemeTemps");
		 		pret.get().setDateDeFin( dateService.modifierDate(pret.get().getDateDeFin(), 2));
		 		 pretRepository.save(pret.get());
		 	 }
		 }
		 
	@Override
	public void modifierStatutsPrets() throws Exception {
		
		ArrayList<Pret>prets=(ArrayList<Pret>) pretService.afficherPrets();
		for(int i = 0; i <= prets.size(); i++) {
			modifierStatut(prets.get(i).getCodePret());
		}
		
		}
	
	
	
@Override
	public void envoieMails() throws Exception {
		ArrayList<Pret>prets=(ArrayList<Pret>) pretService.afficherPrets("depasse");
		if (prets.isEmpty()) {
			throw new Exception("il n'y a pas de mail a envoyer");
		}
		for(int i = 0; i <= prets.size(); i++) {
			mailService.templateMessage();
		}
		
}
}