package sid.org.service;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.data.domain.Page;

import sid.org.classe.Pret;
import sid.org.classe.Utilisateur;
import sid.org.dto.PretDto;
import sid.org.exception.BibliothequeException;
import sid.org.exception.DemandeUtilisateurIncorrectException;
import sid.org.exception.MauvaiseDemandeException;

public interface PretService {

	
	public Pret creerPret(PretDto pretDto, Principal principal) throws BibliothequeException;

	public void supprimerPret(Long id) throws DemandeUtilisateurIncorrectException;

	public Page<Pret> afficherPrets(Utilisateur utilisateur, int page, int size) throws MauvaiseDemandeException;
	public Pret afficherUnPret(Long id) throws Exception;
	public Pret afficherPret(Long id) throws DemandeUtilisateurIncorrectException;
	public List<Pret> afficherPrets() throws DemandeUtilisateurIncorrectException;
	public List<Pret> afficherPrets(String statut) throws DemandeUtilisateurIncorrectException;
	public void modifierStatut(Long id) throws Exception;
	public void modifierStatutsPrets() throws Exception;
	
	
	
	
}
