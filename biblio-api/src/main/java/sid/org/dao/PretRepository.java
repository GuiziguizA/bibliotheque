package sid.org.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import sid.org.classe.Pret;
import sid.org.classe.Utilisateur;

public interface PretRepository extends JpaRepository<Pret, Long>{
	
	 public List<Pret>findByUtilisateur(Utilisateur utilisateur);
	

}
