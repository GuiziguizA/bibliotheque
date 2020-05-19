package sid.org;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sid.org.service.PretAutoService;

@SpringBootApplication
public class BatchAppli implements CommandLineRunner{
	
	
	
    public static void main(String[] args) {
        SpringApplication.run(BatchAppli.class, args);
    }
    @Autowired
	private PretAutoService pretAutoService;
	@Override
	public void run(String... args) throws Exception {
		pretAutoService.modifierStatutsPrets();
		pretAutoService.envoieMails();
	}
  
	}
