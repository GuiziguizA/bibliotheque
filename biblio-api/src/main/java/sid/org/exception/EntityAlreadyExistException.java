package sid.org.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.METHOD_NOT_ALLOWED, reason="Cet element n'existe pas")
public class EntityAlreadyExistException extends BibliothequeException{

	
	public EntityAlreadyExistException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EntityAlreadyExistException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}
