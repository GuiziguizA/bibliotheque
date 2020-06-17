package sid.org.biblio.front.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import sid.org.biblio.front.classe.Livre;
import sid.org.biblio.front.classe.Pret;
import sid.org.biblio.front.config.SimpleAuthenticationFilter;
import sid.org.biblio.front.service.BookService;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Controller

public class BooksController {
	@Autowired
	private BookService bookService;


	

	@GetMapping(value = "/books/form")
	public String Book(Livre livre) {

		return "formulaireLivre";
	}

	@PostMapping("/books")
	public String createBook( Livre livre, BindingResult result, Model model)  {

		
		try {
		bookService.createLivre(livre);
			String succes = "Le livre a été ajouté";
			model.addAttribute("succes",succes);
		} catch (HttpStatusCodeException e) {
			model.addAttribute("error", e);

		}

		return "formulaireLivre";
	}

	@GetMapping(value = "/books")
	public String listBooks(Model model, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(required = false) Optional<Integer> size,
			@RequestParam(required = false) Optional<String> type,
			@RequestParam(required = false) Optional<String> recherche) {
	
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);
		try {
			Page<Livre> bookPage = bookService.livresRecherche(type, recherche, pageSize, currentPage);
			model.addAttribute("bookPage", bookPage);
			int totalPages = bookPage.getTotalPages();
			if (totalPages > 0) {
				List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
				model.addAttribute("pageNumbers", pageNumbers);
				
			}
			model.addAttribute("type", type.get());
			model.addAttribute("recherche", recherche.get());

			return "books";

		} catch (HttpStatusCodeException e) {

			model.addAttribute("error", e);
			return "error";
		}

	}

}
