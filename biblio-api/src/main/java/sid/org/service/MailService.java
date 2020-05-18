package sid.org.service;

import sid.org.exception.FormeMailException;

public interface MailService {
	public String verifierUnMail(String mail) throws  FormeMailException  ;
}
