package sid.org.batch.service;

import java.util.Locale;

import javax.mail.MessagingException;

import sid.org.batch.classe.Pret;

public interface EmailService {
	
	  

	public void sendMail(String biblioMail, String mail, String subject, Pret pret, Locale locale)throws MessagingException;

	public void envoieMails(Locale locale) throws Exception;

	public void modifierStatutPrets();

	

}
