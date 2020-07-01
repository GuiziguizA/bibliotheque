package sid.org.batch;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import sid.org.batch.service.EmailService;


import java.util.Locale;


@SpringBootApplication
@EnableScheduling
public class BatchAppli implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BatchAppli.class, args);
	}

	
	  @Autowired 
	  private EmailService emailService;
	  
	  
	  
	  @Override 
	  public void run(String... args) throws Exception {
	  
	  Locale locale=new Locale("fr");
	  try {
		  emailService.envoieMails(locale);
	} catch (Exception e) {
	e.getMessage();
	}
	
	  
	  
	  }

}
