package sid.org.classe;

import java.util.Collection;



import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement
@Entity

public class Livre {
	@Id  @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long codeLivre;
	@NotBlank(message = "le champ nom ne peut pas etre vide")
	private String nom;
	@NotBlank(message = "le champ auteur ne peut pas etre vide")
	private String auteur;
	@NotBlank(message = "le champ type ne peut pas etre vide")
	private String type;
	@NotBlank(message = "le champ section ne peut pas etre vide")
	private String section;
	@NotBlank(message = "le champ emplacement ne peut pas etre vide")
	private String emplacement;
	@NotNull(message = "le champ nombre d'exemplaire ne peut pas etre vide")
	private int nombreExemplaire;
	
	@OneToMany(mappedBy="livre",fetch=FetchType.LAZY)	
	private Collection<Pret>prets;
	
	public Livre() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Livre(String nom, String auteur,String type, String section, String emplacement, int nombreExemplaire) {
		super();
		this.nom = nom;
		this.auteur = auteur;
		this.type = type;
		this.section = section;
		this.emplacement = emplacement;
		this.nombreExemplaire = nombreExemplaire;
		
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getEmplacement() {
		return emplacement;
	}

	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}

	public int getNombreExemplaire() {
		return nombreExemplaire;
	}

	public void setNombreExemplaire(int nombreExemplaire) {
		this.nombreExemplaire = nombreExemplaire;
	}

	public Long getCodeLivre() {
		return codeLivre;
	}
	
	

	
}
