package sid.org.biblio.front.classe;

import java.util.Collection;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;


@Entity
public class Roles {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long codeRole;
	@NotBlank(message = "le champ nom ne peut pas etre vide")
	private String nom;
	@OneToMany(mappedBy="roles",fetch=FetchType.LAZY)	
	private Collection<Utilisateur>utilisateurs;
	
	public Roles() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Roles(String nom) {
		super();
		this.nom = nom;
	}
	
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public Long getCodeRole() {
		return codeRole;
	}
	
	
}
