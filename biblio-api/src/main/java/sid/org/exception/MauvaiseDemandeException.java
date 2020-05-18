package sid.org.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Cet demande est incorrect")
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
