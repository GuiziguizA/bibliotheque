package sid.org.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import sig.org.classe.Mail;

public interface MailRepository extends JpaRepository<Mail, Long> {

}
