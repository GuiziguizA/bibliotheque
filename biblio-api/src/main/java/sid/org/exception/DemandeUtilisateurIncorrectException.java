package sid.org.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Cet element n'existe pas")
public class DemandeUtilisateurIncorrectException extends BibliothequeException{
	
	public DemandeUtilisateurIncorrectException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DemandeUtilisateurIncorrectException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}
