package sid.org.biblio.front.classe;

import java.util.Date;



public class Pret {

	private Long id;
	private String statut;
	private Livre livre;
	private Date dateDeFin;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	public Livre getLivre() {
		return livre;
	}
	public void setLivre(Livre livre) {
		this.livre = livre;
	}
	public Date getDateDeFin() {
		return dateDeFin;
	}
	public void setDateDeFin(Date dateDeFin) {
		this.dateDeFin = dateDeFin;
	}

	
}
