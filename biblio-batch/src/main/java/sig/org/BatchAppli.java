package sig.org;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;



@SpringBootApplication
@EnableScheduling
public class BatchAppli {

	public static void main(String[] args) {
		SpringApplication.run(BatchAppli.class, args);
	}

	/*
	 * @Autowired private EmailService emailService;
	 * 
	 * @Autowired private PretAutoService pretAutoService;
	 * 
	 * @Override public void run(String... args) throws Exception {
	 * 
	 * 
	 * 
	 * 
	 * emailService.sendSimpleMessage("gua@gmail.com", "go@gmail.com",
	 * "test du mail"); pretAutoService.modifierStatutsPrets();
	 * pretAutoService.envoieMails();
	 * 
	 * }
	 */

}
