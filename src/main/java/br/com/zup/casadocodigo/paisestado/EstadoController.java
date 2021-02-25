package br.com.zup.casadocodigo.paisestado;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@PersistenceContext
	EntityManager manager;
	
	@Autowired
	private ProibeEstadoDuplicadoParaMesmoPaisValidator proibeEstadoDuplicadoParaMesmoPaisValidator;
	
	@InitBinder
	public void Init(WebDataBinder binder) {
		binder.addValidators(proibeEstadoDuplicadoParaMesmoPaisValidator);
	}
	
	@PostMapping
	@Transactional
	public String novoEstado(@RequestBody @Valid NovoEstadoRequest request) {
	Estado novoEstado = request.toModel(manager);
	manager.persist(novoEstado);
	
	return novoEstado.toString();
	}
	
}
