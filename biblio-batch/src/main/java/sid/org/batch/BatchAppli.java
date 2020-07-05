package sid.org.batch;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import sid.org.BiblioAppli;

import java.util.Locale;


@SpringBootApplication(scanBasePackages= {"sid.org","sid.org.batch"})
@EnableScheduling
public class BatchAppli implements CommandLineRunner {
	 private static final Logger logger = LoggerFactory.getLogger(BiblioAppli.class);
	public static void main(String[] args) {
		SpringApplication.run(BatchAppli.class, args);
		logger.info("Biblio-api Started........");
	}

	/*
	 * @Autowired private EmailService emailService;
	 * 
	 */
	  
	  @Override 
	  public void run(String... args) throws Exception {
		/*
		 * Locale locale=new Locale("fr"); try { emailService.envoieMails(locale); }
		 * catch (Exception e) { e.getMessage(); }
		 */
	
	  
	  
	  }

}
