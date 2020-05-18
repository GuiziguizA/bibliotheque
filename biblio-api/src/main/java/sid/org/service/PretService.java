package sid.org.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import sid.org.classe.Pret;
import sid.org.classe.Utilisateur;
import sid.org.exception.BibliothequeException;
import sid.org.exception.DemandeUtilisateurIncorrectException;
import sid.org.exception.MauvaiseDemandeException;

public interface PretService {

	
	public Pret creerPret(Pret pret) throws BibliothequeException ;
	public Pret modifierPret(Long id) throws DemandeUtilisateurIncorrectException;
	public void supprimerPret(Long id) throws DemandeUtilisateurIncorrectException;

	public Page<Pret> afficherPrets(Utilisateur utilisateur, int page, int size) throws MauvaiseDemandeException;
	public Pret afficherUnPret(Long id) throws Exception;
	Pret afficherPret(Long id) throws DemandeUtilisateurIncorrectException;
	
	
	
}
