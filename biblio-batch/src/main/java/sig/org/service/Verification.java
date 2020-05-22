package sig.org.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import sid.org.service.PretService;

@Component
public class Verification {

	
	  @Autowired 
	  private EmailService emailService;
	  
	  @Autowired 
	  private PretAutoService pretAutoService;
	  @Autowired 
	  private PretService pretService;
	  
	@Scheduled(fixedRate=10)
	public void effectuerLesEnvoies() throws Exception {
		
		  emailService.sendSimpleMessage("gua@gmail.com", "go@gmail.com", "test du mail"); 
		  pretService.modifierStatutsPrets();
		  pretAutoService.envoieMails();
		  
		  
		 
		
	}
}
