package sid.org.batch;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


import sid.org.classe.Pret;
import sid.org.exception.ResultNotFoundException;
import sid.org.service.PretService;

@Component
public class PretBatchServiceImpl implements PretBatchService {

	

	@Autowired
	private PretService pretService;
	
	
	
 @Autowired
private EmailService emailService;
 
 @Autowired
  private TemplateEngine templateEngine;
 private static final Logger logger = LoggerFactory.getLogger(PretBatchServiceImpl.class);

@Value("${mail.bibliotheque}")
private String biblioMail;
@Value("${mail.subject}")
private String subject;
@Value("${pret.statut3}") 
  private String statut3;	
	
public Context variableEmail(final Locale locale,Pret pret) {
	 
	 final Context ctx = new Context(locale);
	
	 ctx.setVariable("dateDeFin", pret.getDateDeFin());
	 ctx.setVariable("livre", pret.getLivre().getNom());
	 ctx.setVariable("user", pret.getUtilisateur().getNom());
	return ctx;
	 
}

@Override
public String createHtmlContent(Pret pret,Locale locale) {
	Context ctx=variableEmail(locale, pret);
	
	
	return templateEngine.process("email.html", ctx);
}



 

	
	  @Override 
	  public void envoieMails(Locale locale) throws ResultNotFoundException, MessagingException,IOException {
	  
	  
	  
	  List<Pret>prets=pretService.afficherPrets(statut3);
	  
	  
	  if (!prets.isEmpty()) {
		  for(int i = 0; i < prets.size(); i++) {
	  
	  String htmlContent = createHtmlContent(prets.get(i), locale);
	  emailService.sendMail(biblioMail,prets.get(i).getUtilisateur().getMail(),subject, htmlContent, locale);
	  
	  
	  }}else
		  { logger.info("la liste est vide pour les envoies des mails"); } 
	  
	  
	  }
	 
	
	@Override
	public void modifierStatutPrets() throws ResultNotFoundException {
		
		pretService.modifierStatutsPrets();
	}	
	
	
	

}
