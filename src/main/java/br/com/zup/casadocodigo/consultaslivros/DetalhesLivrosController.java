package br.com.zup.casadocodigo.consultaslivros;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.casadocodigo.crialivros.Livro;

@RestController
public class DetalhesLivrosController {
	
	@PersistenceContext
	private EntityManager manager;

	@GetMapping(value = "/produtos/{id}")
	public DetalheLivroResponse detalhe(@PathVariable("id") Long id) {

		// 1
		Livro livroBuscado = Optional.ofNullable(manager.find(Livro.class, id))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		// 1
		DetalheLivroResponse detalheSiteLivroResponse = new DetalheLivroResponse(
				livroBuscado);
		return detalheSiteLivroResponse;
	}

}