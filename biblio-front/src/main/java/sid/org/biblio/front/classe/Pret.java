package sid.org.biblio.front.classe;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;








public class Pret {

private Date dateDeDebut;
private Date dateDeFin;
private String statut;
private int nombreLivres;

private Livre livre;

private Utilisateur utilisateur;



public Pret() {
	super();
	// TODO Auto-generated constructor stub
}



public Pret(Date dateDeDebut, Date dateDeFin, String statut, int nombreLivres, Livre livre,
		Utilisateur utilisateur) {
	super();
	this.dateDeDebut = dateDeDebut;
	this.dateDeFin = dateDeFin;
	this.statut = statut;
	this.nombreLivres = nombreLivres;
	this.livre = livre;
	this.utilisateur = utilisateur;
}





public int getNombreLivres() {
	return nombreLivres;
}




public void setNombreLivres(int nombreLivres) {
	this.nombreLivres = nombreLivres;
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








}
