package sid.org.batch.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import sid.org.batch.classe.Pret;
import sid.org.exception.ResultNotFoundException;
import sid.org.service.PretService;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Locale;



@Service
public class EmailServiceImpl implements EmailService {


	 @Autowired
	    public JavaMailSender emailSender;
	
	  @Autowired 
	  public PretService pretService;
	 

	 
	 
	   @Autowired
	    private TemplateEngine templateEngine;

	   
	   
		@Value("${api.url}")
		private String apiUrl;
		@Value("${spring.api.identifiant}")
		private String identifiant;
		@Value("${spring.api.motDePasse}")
		private String motDePasse;
		
		@Value("${mail.bibliotheque}")
		private String biblioMail;
		@Value("${mail.subject}")
		private String subject;
		

	 
	 public Context variableEmail(final Locale locale,Pret pret) {
		 
		 final Context ctx = new Context(locale);
		
		 ctx.setVariable("dateDeFin", pret.getDateDeFin());
		 ctx.setVariable("livre", pret.getLivre().getNom());
		 ctx.setVariable("user", pret.getUtilisateur().getNom());
		return ctx;
		 
	 }
	 @Override
	 public void sendMail(String from, String to, String subject,Pret pret,Locale locale) throws MessagingException {
	       


	
	           // Prepare message using a Spring helper
		 		Context  ctx=variableEmail(locale, pret); 
	           final MimeMessage mimeMessage = this.emailSender.createMimeMessage();
	           final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8"); 
	           message.setSubject(subject);
	           message.setFrom(from);
	           message.setTo(to);
	           try {
				pretService.afficherPrets();
			} catch (ResultNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	           // Create the HTML body using Thymeleaf
	           final String htmlContent = this.templateEngine.process("email", ctx);
	           message.setText(htmlContent, true); // true = isHtml

	           this.emailSender.send(mimeMessage);
	          


	        }
	
	

		@Override
	public void envoieMails(Locale locale) throws Exception {
			RestTemplate rt = new RestTemplate();
			final String uri = apiUrl + "/listprets?statut=depasse";
			
		
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setBasicAuth(identifiant, motDePasse);
			ParameterizedTypeReference<List<Pret>> responseType = new ParameterizedTypeReference<List<Pret>>() {
			};
			ResponseEntity<List<Pret>> pret = rt.exchange(uri, HttpMethod.GET, new HttpEntity<>(headers), responseType);
			List<Pret>prets= pret.getBody();
			if (!prets.isEmpty()) {
				for(int i = 0; i < prets.size(); i++) {
					
					 sendMail(biblioMail, prets.get(i).getUtilisateur().getMail(),subject, prets.get(i), locale);
					 
					
				}
			}


		}
		
		@Override
		public void modifierStatutPrets() {
			
			RestTemplate rt = new RestTemplate();
			final String uri = apiUrl + "/prets/statuts";
			

			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setBasicAuth(identifiant, motDePasse);
			rt.postForLocation(uri,new HttpEntity<>(headers));
			
			
		}	
		
		
		@Scheduled ( fixedRate = 100)
		public void effectuerLesEnvoies() throws Exception {
		
			Locale locale = new Locale("fr");
			modifierStatutPrets();
			
			envoieMails(locale); 
			 
			  
			 
			
		}	
		
		
}