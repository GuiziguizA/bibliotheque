package sig.org.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;



@Component
public class Verification {

	
	  @Autowired 
	  private EmailService emailService;
	  
	  @Autowired 
	  private PretAutoService pretAutoService;
	 
	  
	@Scheduled(fixedRate=10)
	public void effectuerLesEnvoies() throws Exception {
		
		 
		  pretAutoService.modifierStatutPrets();
		  pretAutoService.envoieMails();
		  
		  
		 
		
	}
}
