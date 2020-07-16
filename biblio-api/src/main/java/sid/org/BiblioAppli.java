package sid.org;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

import sid.org.batch.EmailService;
import sid.org.batch.PretBatchService;
import sid.org.classe.Livre;
import sid.org.classe.Pret;
import sid.org.classe.Roles;
import sid.org.classe.Utilisateur;
import sid.org.dao.LivreRepository;
import sid.org.dao.PretRepository;
import sid.org.dao.RolesRepository;
import sid.org.dao.UtilisateurRepository;
import sid.org.dto.LivreDto;
import sid.org.dto.UtilisateurDto;

import sid.org.service.DateService;
import sid.org.service.LivreService;

import sid.org.service.PretService;
import sid.org.service.UtilisateurService;

@SpringBootApplication

public class BiblioAppli implements CommandLineRunner  {
	private static final Logger logger = LoggerFactory.getLogger(BiblioAppli.class);

	public static void main(String[] args) {
		SpringApplication.run(BiblioAppli.class, args);
		logger.info("Biblio-api Started........");
	}

	

	@Autowired
	private RolesRepository rolesRepository;

	@Autowired
	private LivreService livreService;
	@Autowired
	private UtilisateurService utilisateurService;
	@Autowired
	private PretService pretService;
	@Autowired
	private PretRepository pretRepository;
	@Autowired
	private LivreRepository livreRepository;
	@Autowired
	private DateService dateService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Override
	public void run(String... args) throws Exception {

		Roles role1 = rolesRepository.save(new Roles("user"));
		Roles role2 = rolesRepository.save(new Roles("employe"));
		Roles role3 = rolesRepository.save(new Roles("admin"));
		Utilisateur admin = utilisateurService.creerUtilisateur(
				new UtilisateurDto("admin", "admin@gmail.com", " adresse1", "admin", "codePostal1"),"admin");
		Utilisateur employe = utilisateurService.creerUtilisateur(
				new UtilisateurDto("employe", "employe@gmail.com", " adresse1", "employe", "codePostal1"),"employe");
		Utilisateur user1 = utilisateurService.creerUtilisateur(
				new UtilisateurDto("nom1", "gualisse@gmail.com", " adresse1", "motDePasse1", "codePostal1"),"user");
		Utilisateur user2 = utilisateurService.creerUtilisateur(
				new UtilisateurDto("nom2", "guzalfisse@gmail.com", " adresse2", "motDePasse2", "codePostal2"),"user");
		Utilisateur user3 = utilisateurService.creerUtilisateur(
				new UtilisateurDto("nom3", "gfusalisse@gmail.com", " adresse3", "motDePasse3", "codePostal3"),"user");
		Utilisateur user4 = utilisateurService.creerUtilisateur(
				new UtilisateurDto("nom4", "gusalissfe@gmail.com", " adresse4", "motDePasse4", "codePostal4"),"user");
		Utilisateur user5 = utilisateurService.creerUtilisateur(
				new UtilisateurDto("nom5", "gwalfisse@gmail.com", " adresse5", "motDePasse5", "codePostal5"),"user");
		Utilisateur user6 = utilisateurService.creerUtilisateur(
				new UtilisateurDto("nom6", "guxavlisse@gmail.com", " adresse6", "motDePasse6", "codePostal6"),"user");
		utilisateurService.creerUtilisateur(new UtilisateurDto("front", "front", " adresse7", "front", "codePostal7"),"user");
		utilisateurService.creerUtilisateur(new UtilisateurDto("batch", "batch", " adresse7", "batch", "codePostal7"),"user");
		Livre livre1 = livreService
				.createLivre(new LivreDto("le bossu", "auteur1", "type1", "section1", "emplacement1", 3));
		Livre livre2 = livreService
				.createLivre(new LivreDto("la merguez", "auteur2", "type2", "section2", "emplacement1", 4));
		Livre livre3 = livreService
				.createLivre(new LivreDto("le bossu de notre dame", "auteur1", "type1", "section1", "emplacement1", 5));
		Livre livre4 = livreService
				.createLivre(new LivreDto("nom4", "auteur4", "type1", "section1", "emplacement1", 7));

		Pret pret = pretRepository.save(new Pret(new Date(), new Date(), "encours", 1, livre1, user4));
		Pret pret1 = pretRepository.save(new Pret(new Date(), new Date(), "encours", 1, livre1, user4));
		Pret pret2 = pretRepository.save(new Pret(new Date(), new Date(), "encours", 1, livre2, user1));
		Pret pret3 = pretRepository.save(new Pret(new Date(), new Date(), "encours", 1, livre3, user1));
		Pret pret5 = pretRepository.save(new Pret(new Date(), new Date(), "depasse", 1, livre1, user1));
	  
	  }
	 
}
