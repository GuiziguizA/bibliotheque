package sid.org.metier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sid.org.classe.Pret;
import sid.org.classe.Utilisateur;
import sid.org.dao.PretRepository;
import sid.org.dao.UtilisateurRepository;
import sid.org.service.IUtilisateur;

@Service
public class UtilisateurService implements IUtilisateur {
	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Autowired
	private PretRepository pretRepository;
	
	@Override
	public Utilisateur creerUtilisateur(Utilisateur utilisateur) throws Exception {
		Optional<Utilisateur> user =utilisateurRepository.findByMail(utilisateur.getMail());
		if(user.isPresent()) {
			throw new Exception("le mail est deja utilise");
		}
		return utilisateurRepository.save(utilisateur);
	}

	@Override
	public Utilisateur modifierUtilisateur(Long id, String motDePasse) throws Exception {
		Optional<Utilisateur> user =utilisateurRepository.findById(id);
		
		if(!user.isPresent()) {
			throw new Exception("Utilisateur introuvable");
		}
		user.get().setMotDePasse(motDePasse);
		
		return utilisateurRepository.save(user.get());
	}

	@Override
	@Transactional
	public void supprimerUtilisateur(Long id) throws Exception {
Optional<Utilisateur> user =utilisateurRepository.findById(id);
List<Pret>listPretUtilisateur=pretRepository.findByUtilisateur(user.get());		
		if(!user.isPresent()) {
			throw new Exception("Utilisateur introuvable");
		}
	
		utilisateurRepository.delete(user.get());
		pretRepository.deleteAll(listPretUtilisateur);
		
	}
	
	@Override
	public Map<String, Object> voirUtilisateur(Long id) throws Exception {
		

		  Map<String, Object> utilisateur=new HashMap<>();
		Optional<Utilisateur>user= utilisateurRepository.findById(id);
		if(!user.isPresent()) {
			throw new Exception("Utilisateur introuvable");
		}
		
		
		  utilisateur.put("utilisateur",user );
	
	return utilisateur;
	}

	@Override
	public Map<String, Object> voirListeUtilisateurs() throws Exception {
		

		  Map<String, Object> utilisateurs=new HashMap<>();
		  ArrayList<Utilisateur>listeUtilisateurs=(ArrayList<Utilisateur>) utilisateurRepository.findAll();
		  if(!listeUtilisateurs.isEmpty()) {
				throw new Exception("Il n'y a pas encore d'utilisateurs");
			}
		  utilisateurs.put("utilisateurs",listeUtilisateurs );
	
	return utilisateurs;
	}

}
