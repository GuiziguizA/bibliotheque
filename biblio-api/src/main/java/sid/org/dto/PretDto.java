package sid.org.dto;

import javax.validation.constraints.NotNull;

import sid.org.classe.Livre;
import sid.org.classe.Utilisateur;

/**
 * @author guali
 *
 */
public class PretDto {
	
	@NotNull(message = "il manque le livre")
	private Livre livre;
	@NotNull(message = "il manque le nombre de livre emprunté")
	private int nombreLivres;
	
	
	
	public PretDto( Livre livre,
		 int nombreLivres) {
		super();
		this.livre = livre;
		this.nombreLivres = nombreLivres;
	}



	public PretDto(Livre livre) {
		super();
		this.livre = livre;
		
	}
	
	
	
	public int getNombreLivres() {
		return nombreLivres;
	}



	public void setNombreLivres(int nombreLivres) {
		this.nombreLivres = nombreLivres;
	}



	public Livre getLivre() {
		return livre;
	}
	public void setLivre(Livre livre) {
		this.livre = livre;
	}

	
	
	
}
