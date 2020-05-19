package sid.org.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import sid.org.classe.Pret;
import sid.org.classe.Utilisateur;

public interface PretRepository extends JpaRepository<Pret, Long>{
	
	 public Page<Pret>findByUtilisateur(Utilisateur utilisateur,Pageable pageable);
	 public List<Pret>findByStatut(String statut);
		

}
