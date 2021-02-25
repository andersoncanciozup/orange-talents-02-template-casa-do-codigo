package br.com.zup.casadocodigo.pagamento;

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
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private EstadoPertenceAPaisValidator estadoPertenceAPaisValidator;
	
	@PersistenceContext
	EntityManager manager;

	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(estadoPertenceAPaisValidator);
	}
	
	@PostMapping()
	@Transactional
	public String criaCliente(@RequestBody @Valid CadastroClienteRequest request) {
		Cliente novoCliente = request.toModel(manager);
		manager.persist(novoCliente);
		return novoCliente.toString();

	}
}
