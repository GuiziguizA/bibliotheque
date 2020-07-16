package sid.org.batch;

import java.io.IOException;
import java.util.Locale;

import javax.mail.MessagingException;

import sid.org.classe.Pret;
import sid.org.exception.ResultNotFoundException;

public interface PretBatchService {

	
	  void envoieMails(Locale locale) throws ResultNotFoundException,MessagingException, IOException;
	 
	void modifierStatutPrets() throws ResultNotFoundException;

	String createHtmlContent(Pret pret, Locale locale);

}
