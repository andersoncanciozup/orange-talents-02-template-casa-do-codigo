package br.com.zup.casadocodigo.consultaslivro;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.casadocodigo.crialivro.Livro;

@RestController
public class DetalhesLivrosController {
	
	@PersistenceContext
	private EntityManager manager;

	@GetMapping(value = "/produtos/{id}")
	public DetalheLivroResponse detalhe(@PathVariable("id") Long id) {

	
		Livro livroBuscado = Optional.ofNullable(manager.find(Livro.class, id))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));


		DetalheLivroResponse detalheSiteLivroResponse = new DetalheLivroResponse(
				livroBuscado);
		return detalheSiteLivroResponse;
	}

	@GetMapping(value = "/livros")
	public List<LivroResponse> listaLivros() {
		List<Livro> livros = manager.createQuery("FROM Livro", Livro.class).getResultList();
		return livros.stream().map(LivroResponse::new).collect(Collectors.toList());
	}

}