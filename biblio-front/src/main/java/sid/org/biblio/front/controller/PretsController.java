package sid.org.biblio.front.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;

import sid.org.biblio.front.classe.Pret;
import sid.org.biblio.front.service.PretService;

@Controller
public class PretsController {
	@Autowired
	private PretService pretService;

	@GetMapping(value = "/prets")
	public String pretUtilisateur(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size,HttpServletRequest request) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);
		HttpSession session = request.getSession();
		String motDePasse=(String) session.getAttribute("password");
		String mail=(String) session.getAttribute("username");

		try {
			Page<Pret> prets = pretService.pretsUtilisateur(mail, currentPage, pageSize,motDePasse);
			model.addAttribute("prets", prets);

			int totalPages = prets.getTotalPages();
			if (totalPages > 0) {
				List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
				model.addAttribute("pageNumbers", pageNumbers);
			}

		} catch (Exception e) {

			model.addAttribute("error", e);
		}

		return "prets";
	}

	@PostMapping(value = "/prets", consumes = "application/x-www-form-urlencoded")
	public String creerUnPret(@RequestParam Long id, Model model,Principal principal,HttpServletRequest request) throws Exception {
		Pret pret = new Pret();
		pret.setId(id);
		
		HttpSession session = request.getSession();
		String motDePasse=(String) session.getAttribute("password");
		String mail=(String) session.getAttribute("username");
		try {
			pretService.creerPret(pret,principal.getName(),motDePasse);
			return "succesOperation";
		} catch (HttpStatusCodeException e) {

			model.addAttribute("error", e);
			return "error";
		}

	}
	
	@PutMapping(value = "/prets/{id}")
	public String modifierPrets(@PathVariable Long id,Model model,Principal principal,HttpServletRequest request) {
		
		
		HttpSession session = request.getSession();
		String motDePasse=(String) session.getAttribute("password");
		String mail=(String) session.getAttribute("username");
		try {
			pretService.modifierUnPret(id, mail, motDePasse);
		
			return "succesOperation";
		} catch (HttpStatusCodeException e) {
			model.addAttribute(e);
			return"error";
		}
		
		
	}
	
	
}