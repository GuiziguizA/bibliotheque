package sig.org.metier;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;




@Service
public class MailService{


	
	
	
	    @Bean
	    public JavaMailSender javaMailSender() {
	        JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
	        javaMailSenderImpl.setHost("localhost");
	        javaMailSenderImpl.setUsername("");
	        javaMailSenderImpl.setPassword("");
	        javaMailSenderImpl.setProtocol("smtp");
	        javaMailSenderImpl.setPort(25);
	        return javaMailSenderImpl;
	    }

	    @Bean
	    public SimpleMailMessage templateMessage() {
	        SimpleMailMessage mailMessage = new SimpleMailMessage();
	        mailMessage.setTo("gualisse@gmail.com");
	        mailMessage.setSubject("Job Status");
	        mailMessage.setFrom("gualisse@gmail.com");
	        mailMessage.setText("envoie du mail");
	        return mailMessage;
	    }



	
	
	
}
