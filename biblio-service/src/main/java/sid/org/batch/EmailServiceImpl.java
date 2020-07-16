package sid.org.batch;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;




import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


import org.springframework.stereotype.Service;


import sid.org.service.PretService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import org.thymeleaf.TemplateEngine;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class EmailServiceImpl implements EmailService  {


	 @Autowired
	    private JavaMailSender emailSender;

	 
	 
	
	
		
		@Value("${mail.bibliotheque}")
		private String biblioMail;
		@Value("${mail.subject}")
		private String subject;
		@Value("${pret.statut3}") 
		  private String statut3;	

		 private static final Logger logger = LoggerFactory.getLogger( EmailServiceImpl.class);
		 
		 
	  @Override 
	  public void sendMail(String from, String to, String subject,String htmlContent,Locale locale) throws MessagingException, IOException{
	  try {
		  
	  MimeMessage mimeMessage = this.emailSender.createMimeMessage();
	   MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
	  
	  message.setSubject(subject);
	  message.setFrom(from); 
	  message.setTo(to);
	  message.setText(htmlContent, true); // true = isHtml
	  
	  this.emailSender.send(mimeMessage);
	  } catch (MessagingException ex) {
        logger.info(ex.getMessage());
      }

	  
	  
	  }
	 
	  
	  
	/*
	 * public Context variableEmail(final Locale locale,Pret pret) {
	 * 
	 * final Context ctx = new Context(locale);
	 * 
	 * ctx.setVariable("dateDeFin", pret.getDateDeFin()); ctx.setVariable("livre",
	 * pret.getLivre().getNom()); ctx.setVariable("user",
	 * pret.getUtilisateur().getNom()); return ctx;
	 * 
	 * }
	 * 
	 * @Override public void sendMail(String from, String to, String subject,Pret
	 * pret,Locale locale) throws MessagingException {
	 * 
	 * 
	 * 
	 * 
	 * // Prepare message using a Spring helper Context ctx=variableEmail(locale,
	 * pret); final MimeMessage mimeMessage = this.emailSender.createMimeMessage();
	 * final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true,
	 * "UTF-8"); message.setSubject(subject); message.setFrom(from);
	 * message.setTo(to); try { pretService.afficherPrets(); } catch
	 * (ResultNotFoundException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } // Create the HTML body using Thymeleaf final String
	 * htmlContent = this.templateEngine.process("email", ctx);
	 * message.setText(htmlContent, true); // true = isHtml
	 * 
	 * this.emailSender.send(mimeMessage);
	 * 
	 * 
	 * 
	 * }
	 */
		
	
		
	
		
}