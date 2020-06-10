package sid.org.biblio.front.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpStatusCodeException;

import sid.org.biblio.front.classe.Pret;
import sid.org.biblio.front.service.PretService;
@Controller
public class PretsController {
	@Autowired
	private PretService pretService;
	
	   @GetMapping(value = "/prets")
	    public String pretUtilisateur(Model model, @RequestParam("page") Optional<Integer> page, 
          @RequestParam("size") Optional<Integer> size) {
            int currentPage = page.orElse(1);
            int pageSize = size.orElse(2);
		   String mail="gualisse@gmail.com";
		   
		   try {
			Page<Pret>prets=pretService.pretsUtilisateur(mail, currentPage, pageSize);
			model.addAttribute("prets", prets);
			
			  int totalPages = prets.getTotalPages();
			  if (totalPages > 0) {
				  List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages) .boxed()
			  .collect(Collectors.toList()); model.addAttribute("pageNumbers",pageNumbers); }
			 
			
		} catch (Exception e) {
			
			model.addAttribute("error", e);
		}
		   
	   
	      
	   return "prets";
}
	   @PostMapping(value = "/prets",  consumes = "application/x-www-form-urlencoded")
	   public  String creerUnPret(  @RequestParam Long id,Model model) {
		   Pret pret=new Pret();
		   pret.setId(id);
		   try {
			pretService.creerPret(pret);
			return "pretCreation";
		} catch (HttpStatusCodeException e) {
		
			model.addAttribute("error",e);
			return "error";
		}
		   
		
		   
	   }
}