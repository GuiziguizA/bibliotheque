package sid.org.mvc.exception;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.swagger.annotations.ApiOperation;
import sid.org.exception.EntityAlreadyExistException;
import sid.org.exception.LivreIndisponibleException;
import sid.org.exception.MotDePasseInvalidException;
import sid.org.exception.ResultNotFoundException;


@ControllerAdvice
public class ExceptionHandlerControllerAdvice {
	
	@ExceptionHandler(ResultNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody ExceptionReponse ResourceNotFound(final ResultNotFoundException exception,
			final HttpServletRequest request) {

		ExceptionReponse error=new ExceptionReponse();
		error.setMessage(exception.getMessage());
		error.setDate(new Date());
		error.setHttpCodeMessage(request.getRequestURI());
		error.setStatus(404);
		
		return error;
	}
	
	
	

	@ExceptionHandler(EntityAlreadyExistException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public @ResponseBody ExceptionReponse EntityAlreadyExistException(final EntityAlreadyExistException exception,
			final HttpServletRequest request) {

		ExceptionReponse error=new ExceptionReponse();
		error.setMessage(exception.getMessage());
		error.setDate(new Date());
		error.setHttpCodeMessage(request.getRequestURI());
		error.setStatus(409);
		return error;
	}
	
	
	
	
	@ExceptionHandler(LivreIndisponibleException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public @ResponseBody ExceptionReponse ResourceNotFound(final LivreIndisponibleException exception,
			final HttpServletRequest request) {

		ExceptionReponse error=new ExceptionReponse();
		error.setMessage(exception.getMessage());
		error.setDate(new Date());
		error.setHttpCodeMessage(request.getRequestURI());
		error.setStatus(430);
		return error;
	}
	
	@ExceptionHandler(MotDePasseInvalidException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public @ResponseBody ExceptionReponse ResourceNotFound(final MotDePasseInvalidException exception,
			final HttpServletRequest request) {

		ExceptionReponse error=new ExceptionReponse();
		error.setMessage(exception.getMessage());
		error.setDate(new Date());
		error.setHttpCodeMessage(request.getRequestURI());
		error.setStatus(403);
		return error;
	}
	
	
	
}