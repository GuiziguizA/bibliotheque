package sid.org.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import sid.org.classe.Livre;
import sid.org.classe.Pret;
import sid.org.classe.Utilisateur;
import sid.org.dao.PretRepository;
import sid.org.dao.UtilisateurRepository;
import sid.org.exception.BibliothequeException;
import sid.org.exception.DemandeUtilisateurIncorrectException;
import sid.org.exception.UtilisateurMailExistException;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
private MailService mailService;
	@Autowired
	private PretRepository pretRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public Utilisateur creerUtilisateur(Utilisateur utilisateur) throws BibliothequeException{
		Optional<Utilisateur> user =utilisateurRepository.findByMail(utilisateur.getMail());
		if(user.isPresent()) {
			throw new UtilisateurMailExistException("le mail est deja utilise");
		}
		
		mailService.verifierUnMail(utilisateur.getMail());
		utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
		return utilisateurRepository.save(utilisateur);
	}

	@Override
	public Utilisateur modifierUtilisateur(Long id, String motDePasse) throws DemandeUtilisateurIncorrectException {
		Optional<Utilisateur> user =utilisateurRepository.findById(id);
		
		if(!user.isPresent()) {
			throw new DemandeUtilisateurIncorrectException("Utilisateur introuvable");
		}
		user.get().setMotDePasse(passwordEncoder.encode(motDePasse));
		
		return utilisateurRepository.save(user.get());
	}

	@Override
	@Transactional
	public void supprimerUtilisateur(Long id) throws DemandeUtilisateurIncorrectException {
Optional<Utilisateur> user =utilisateurRepository.findById(id);
Pageable pageable=PageRequest.of(2, 5);
Page<Pret>listPretUtilisateur=pretRepository.findByUtilisateur(user.get(),pageable);		
		if(!user.isPresent()) {
			throw new DemandeUtilisateurIncorrectException("Utilisateur introuvable");
		}
	
		utilisateurRepository.delete(user.get());
		pretRepository.deleteAll(listPretUtilisateur);
		
	}
	
	@Override
	public Map<String, Object> voirUtilisateur(Long id) throws DemandeUtilisateurIncorrectException {
		

		  Map<String, Object> utilisateur=new HashMap<>();
		Optional<Utilisateur>user= utilisateurRepository.findById(id);
		if(!user.isPresent()) {
			throw new DemandeUtilisateurIncorrectException("Utilisateur introuvable");
		}
		
		
		  utilisateur.put("utilisateur",user );
	
	return utilisateur;
	}

	@Override
	public Page<Utilisateur> voirListeUtilisateurs(int page, int size){
		
Pageable pageable =PageRequest.of(page,size );
		  Page<Utilisateur> utilisateurs=utilisateurRepository.findAll(pageable);
		  
		
	
	return utilisateurs;
	}

	

}
