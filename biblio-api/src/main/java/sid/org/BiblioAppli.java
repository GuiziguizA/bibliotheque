package sid.org;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.domain.Specification;

import sid.org.classe.Livre;
import sid.org.classe.Pret;
import sid.org.classe.Roles;
import sid.org.classe.Utilisateur;
import sid.org.dao.LivreRepository;
import sid.org.dao.PretRepository;
import sid.org.dao.RolesRepository;
import sid.org.service.DateService;
import sid.org.service.LivreService;

import sid.org.service.PretService;
import sid.org.service.UtilisateurService;




@SpringBootApplication
public class BiblioAppli implements CommandLineRunner{
	
    public static void main(String[] args) {
        SpringApplication.run(BiblioAppli.class, args);
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
  
	
	@Override
	
	public void run(String... args) throws Exception {
	
		Roles role1 = rolesRepository.save(new Roles("user"));
		Roles role2 = rolesRepository.save(new Roles("employe"));
		Roles role3 = rolesRepository.save(new Roles("admin"));
		
		Utilisateur user1= utilisateurService.creerUtilisateur(new Utilisateur( "nom1", "gualisse@gmail.com"," adresse1", "motDePasse1", "codePostal1", role1));
		Utilisateur user2= utilisateurService.creerUtilisateur(new Utilisateur( "nom2","guzalfisse@gmail.com"," adresse2", "motDePasse2", "codePostal2", role1));
		Utilisateur user3= utilisateurService.creerUtilisateur(new Utilisateur("nom3", "gfusalisse@gmail.com"," adresse3", "motDePasse3", "codePostal3", role2));
		Utilisateur user4= utilisateurService.creerUtilisateur(new Utilisateur( "nom4", "gusalissfe@gmail.com"," adresse4", "motDePasse4", "codePostal4", role2));
		Utilisateur user5= utilisateurService.creerUtilisateur(new Utilisateur("nom5", "gwalfisse@gmail.com"," adresse5", "motDePasse5", "codePostal5",  role3));
		Utilisateur user6= utilisateurService.creerUtilisateur(new Utilisateur( "nom6", "guxavlisse@gmail.com"," adresse6", "motDePasse6", "codePostal6",role3));
		
		
		Livre livre1=livreService.createLivre(new Livre("le bossu","auteur1","type1","section1","emplacement1",3));
		Livre livre2=livreService.createLivre(new Livre("la merguez","auteur2","type2","section2","emplacement1",4));
		Livre livre3=livreService.createLivre(new Livre("le bossu de notre dame","auteur1","type1","section1","emplacement1",5));
		Livre livre4=livreService.createLivre(new Livre("nom4","auteur4","type1","section1","emplacement1",7));
		
		/*
		 * Pret pret = pretRepository.save(new Pret(new Date(), new Date(), "statut1",
		 * livre1, user4)); Pret pret1= pretRepository.save(new Pret(new Date(), new
		 * Date(), "statut1", livre1, user4)); Pret pret2= pretRepository.save(new
		 * Pret(new Date(), new Date(), "statut1", livre2, user4)); Pret pret3=
		 * pretRepository.save(new Pret( new Date(), new Date(), "statut1", livre3,
		 * user3));
		 * 
		 * Pret pret5 = pretRepository.save(new Pret(new Date(), new Date(), "statut4",
		 * livre1, user1));
		 * 
		 * Pret pret6 = pretRepository.save(new Pret(new Date(), new Date(), "statut5",
		 * livre1, user1));
		 */
		/*
		 * System.out.println("yolo"); Pret pret7 =pretService.creerPret(new Pret(null,
		 * null, "statut1", livre4, user4));
		 * 
		 * utilisateurService.modifierUtilisateur(user1.getCodeUtilisateur(), "MPD");
		 * utilisateurService.supprimerUtilisateur(user1.getCodeUtilisateur());
		 * Map<String, Object>listU=utilisateurService.voirListeUtilisateurs();
		 */
		/*
		 * List<Livre>listLivres=livreService.AfficherListLivres(null, null);
		 * livreService.ModificationStatutDateDeRetour(livre1.getCodeLivre(),
		 * "satutTest"); Livre
		 * livre=livreService.AfficherLivreDetails(livre1.getCodeLivre());
		 * System.out.println(livre.getNom());
		 * System.out.println(listLivres.get(1).getNom());
		 */
		
		/*
		 * pretService.modifierPret(pret1.getCodePret());
		 * pretService.supprimerPret(pret2.getCodePret());
		 * List<Pret>liste=pretService.afficherPrets(user3);
		 * System.out.println(liste.get(0).getLivre().getNom()); Map<String,
		 * Object>listLivres=livreService.afficherLivres("nom1", null);
		 */
		 
			/*
			 * Iterator iterator = listLivres.entrySet().iterator(); while
			 * (iterator.hasNext()) { Map.Entry mapentry = (Map.Entry) iterator.next();
			 * System.out.println("clé: "+mapentry.getKey() + " | valeur: " +
			 * mapentry.getValue()); }
			 */
	Date date1=dateService.modifierDate(new Date(),8);
		System.out.println(date1);
		ArrayList<Livre>listLivres=livreRepository.findByNomAndtype(null, null);
		

		System.out.println("hello");
		System.out.println(listLivres.get(0).getNom());
		System.out.println(listLivres.get(0).getType());
		System.out.println(listLivres.get(2).getNom());
		
	
	
	}
	}
