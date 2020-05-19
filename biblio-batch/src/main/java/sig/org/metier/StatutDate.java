package sig.org.metier;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

public class StatutDate {
	@Autowired
	private PretService pretService;
	@Autowired
	private MailService mailService;
	
	
	@Override
	public void modifierStatutsPrets() throws Exception {
		
		ArrayList<Pret>prets=pretService.afficherTousLesPrets();
		for(int i = 0; i <= prets.size(); i++) {
mailService.modifierStatut(prets.get(i).getCodePret());
		
		}
	}
	
	@Override
	public void envoieMails() throws Exception {
		ArrayList<Pret>prets=pretService.afficherPretsStatut("depasse");
		for(int i = 0; i <= prets.size(); i++) {
			mailService.envoieMail(prets.get(i).getUtilisateur().getMail(), "rendre le"+prets.get(i).getLivre().getNom(), "Bonjour Vous devez ramener le livre a la bibliotheque");
			
		}
		
}
