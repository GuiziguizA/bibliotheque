package sid.org.classe;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;






@Entity

public class Pret {
@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
private Long codePret;	
private Date dateDeDebut;
private Date dateDeFin;
private String statut;
@ManyToOne
@JoinColumn(name = "ID_Livre")
private Livre livre;
@ManyToOne
@JoinColumn(name = "ID_UTILISATEUR")
private Utilisateur utilisateur;



public Pret() {
	super();
	// TODO Auto-generated constructor stub
}



public Pret(Date dateDeDebut, Date dateDeFin, String statut, Livre livre, Utilisateur utilisateur) {
	super();

	this.dateDeDebut = dateDeDebut;
	this.dateDeFin = dateDeFin;
	this.statut = statut;
	this.livre = livre;
	this.utilisateur = utilisateur;
}



public Date getDateDeDebut() {
	return dateDeDebut;
}



public void setDateDeDebut(Date dateDeDebut) {
	this.dateDeDebut = dateDeDebut;
}



public Date getDateDeFin() {
	return dateDeFin;
}



public void setDateDeFin(Date dateDeFin) {
	this.dateDeFin = dateDeFin;
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



public Utilisateur getUtilisateur() {
	return utilisateur;
}



public void setUtilisateur(Utilisateur utilisateur) {
	this.utilisateur = utilisateur;
}



public Long getCodePret() {
	return codePret;
}




}
