package br.com.zup.casadocodigo.crialivros;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livros")
public class LivroController {

	@PersistenceContext
	private EntityManager manager;

	@PostMapping
	@Transactional
	public String criaLivro(@RequestBody @Valid NovoLivroRequest request) {
		Livro novoLivro = request.toModel(manager);
		manager.persist(novoLivro);
		return novoLivro.toString();
	}

	@GetMapping
	public List<LivroResponse> listaLivros() {
		return converter(manager.createQuery("FROM Livro ", Livro.class).getResultList());
	}

	public static List<LivroResponse> converter(List<Livro> livros) { 
		return livros.stream()
				.map(LivroResponse::new)
				.collect(Collectors.toList()); 
	}

}
