package sid.org.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Cet element n'existe pas")
public class MauvaiseDemandeException extends BibliothequeException {
	
	public MauvaiseDemandeException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MauvaiseDemandeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
