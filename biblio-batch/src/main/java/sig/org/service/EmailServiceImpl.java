package sig.org.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class EmailServiceImpl implements EmailService {


	 @Autowired
	    public JavaMailSender emailSender;
	 
	 public void sendMail(String from, String to, String subject, String msg) {
	        try {

	            MimeMessage message = emailSender.createMimeMessage();

	            message.setSubject(subject);
	            MimeMessageHelper helper;
	            helper = new MimeMessageHelper(message, true);
	            helper.setFrom(from);
	            helper.setTo(to);
	            helper.setText(msg, true);
	           emailSender.send(message);
	        } catch (MessagingException ex) {
	            Logger.getLogger(EmailServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
	        }
	
	


	
	
	
	 }


}
