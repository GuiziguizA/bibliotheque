package sid.org.specification;



public class LivreCriteria {
	private Long codeLivre;
	
	private String nom;
	
	private String auteur;

	private String type;
	
	private String section;
	
	private String emplacement;
	
	private int nombreExemplaire;

	public Long getCodeLivre() {
		return codeLivre;
	}

	public void setCodeLivre(Long codeLivre) {
		this.codeLivre = codeLivre;
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
	
	
	
}
