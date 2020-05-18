package sid.org.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import sid.org.exception.FormeMailException;
@Service
public class MailServiceImpl implements MailService{
	
	@Override
	public String verifierUnMail(String mail) throws FormeMailException {
		
		String regex = "^(.+)@(.+)$";
		 
		Pattern pattern = Pattern.compile(regex);
		  Matcher matcher = pattern.matcher(mail);
		  if(!matcher.matches()) {
			  
			  throw new FormeMailException("l'adresse mail n'est pas valide");
		  }
		 
		return mail;
		
	}

}
