package sid.org.batch.service;

import java.util.Locale;

import javax.mail.MessagingException;

import sid.org.batch.classe.Pret;

public interface EmailService {
	
	  


	public void envoieMails(Locale locale) throws Exception;

	public void modifierStatutPrets();
	
	public void sendMail(String from, String to, String subject, Pret pret, Locale locale) throws MessagingException;

	

	

}
