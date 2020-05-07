package sig.org.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sid.org.dao.MailRepository;
import sig.org.classe.Mail;
import sig.org.service.Imail;

@Service
public class MailService implements Imail {

	@Autowired
	private MailRepository mailRepository;
	
	
	@Override
	public Mail createMail(Mail mail) {
		
		return mailRepository.save(mail);
	}

	
	
	
}
