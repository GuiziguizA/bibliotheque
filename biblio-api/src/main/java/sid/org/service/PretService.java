package sid.org.service;

import java.util.List;
import java.util.Map;

import sid.org.classe.Pret;
import sid.org.classe.Utilisateur;

public interface PretService {

	
	public Pret creerPret(Pret pret) throws Exception ;
	public Pret modifierPret(Long id) throws Exception;
	public void supprimerPret(Long id) throws Exception;
	public List<Pret>afficherPrets(Utilisateur utilisateur) throws Exception;
	public Map<String, Object> afficherPret(Long id) throws Exception;
	public Pret afficherUnPret(Long id) throws Exception;
	
	
}
