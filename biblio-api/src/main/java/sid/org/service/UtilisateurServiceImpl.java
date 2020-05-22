package sid.org.service;



import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import sid.org.classe.Livre;
import sid.org.classe.Pret;
import sid.org.classe.Roles;
import sid.org.classe.Utilisateur;
import sid.org.dao.PretRepository;
import sid.org.dao.RolesRepository;
import sid.org.dao.UtilisateurRepository;
import sid.org.dto.UtilisateurDto;
import sid.org.exception.BibliothequeException;
import sid.org.exception.DemandeUtilisateurIncorrectException;
import sid.org.exception.MauvaiseDemandeException;
import sid.org.exception.UtilisateurMailExistException;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {
	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Autowired
	private PretRepository pretRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RolesRepository rolesRepository;
	
	@Override
	public Utilisateur creerUtilisateur(UtilisateurDto utilisateurDto) throws BibliothequeException{
		Optional<Utilisateur> user =utilisateurRepository.findByMail(utilisateurDto.getMail());
		if(user.isPresent()) {
			throw new UtilisateurMailExistException("le mail est deja utilise");
		}
		
	
		utilisateurDto.setMotDePasse(passwordEncoder.encode(utilisateurDto.getMotDePasse()));
		Utilisateur utilisateur= convertToUtilisateur(utilisateurDto);
		Optional<Roles> roles=rolesRepository.findByNom("user");
		utilisateur.setRoles(roles.get());
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
	public Utilisateur voirUtilisateur(Long id) throws DemandeUtilisateurIncorrectException {
		

		
		Optional<Utilisateur>user= utilisateurRepository.findById(id);
		if(!user.isPresent()) {
			throw new DemandeUtilisateurIncorrectException("Utilisateur introuvable");
		}
		
	
	
	return user.get();
	}

	@Override
	public Page<Utilisateur> voirListeUtilisateurs(int page, int size) throws MauvaiseDemandeException{
		if(size==0) {
			throw new MauvaiseDemandeException();
		}
Pageable pageable =PageRequest.of(page,size );
		  Page<Utilisateur> utilisateurs=utilisateurRepository.findAll(pageable);
		  
		
	
	return utilisateurs;
	}
private Utilisateur convertToUtilisateur(UtilisateurDto utilisateurDto) {
	Utilisateur utilisateur = new Utilisateur();
	utilisateur.setAdresse(utilisateurDto.getAdresse());
	utilisateur.setCodePostal(utilisateurDto.getCodePostal());
	utilisateur.setMail(utilisateurDto.getMail());
	utilisateur.setMotDePasse(utilisateurDto.getMotDePasse());
	utilisateur.setNom(utilisateurDto.getNom());
	return utilisateur;
	
	
	
}

}
