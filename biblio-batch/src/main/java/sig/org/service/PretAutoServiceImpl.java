package sig.org.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sid.org.classe.Pret;
import sid.org.service.PretService;

@Service
public class PretAutoServiceImpl implements PretAutoService{
	@Autowired
	private PretService pretService;
	@Autowired
	private EmailService emailService;

	

	
	@Override
public void envoieMails() throws Exception {
		ArrayList<Pret>prets=(ArrayList<Pret>) pretService.afficherPrets("depasse");
		if (prets.isEmpty()) {
			throw new Exception("il n'y a pas de mail a envoyer");
		}
		for(int i = 0; i < prets.size(); i++) {
			emailService.sendSimpleMessage("bibliothequeMuni@gmail.com",prets.get(i).getUtilisateur().getMail(),"le livre "+prets.get(i).getLivre().getNom()	+" doit etre rendu au plus vite car vous avez depassé la date de pret");
			
		}
		
}
}