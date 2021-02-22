package br.com.zup.casadocodigo.criaautor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autores")
public class AutorController {
	
	@PersistenceContext
    private EntityManager manager;
	
	@PostMapping
	@Transactional
	public String criaAutor(@RequestBody @Valid NovoAutorRequest request) {
		Autor autor = request.toModel();
		manager.persist(autor);
		return autor.toString();
	}

}
