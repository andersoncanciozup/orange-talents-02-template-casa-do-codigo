package br.com.zup.casadocodigo.pagamento;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zup.casadocodigo.paisestado.Estado;
import br.com.zup.casadocodigo.paisestado.Pais;

@Component
public class EstadoPertenceAPaisValidator implements Validator {
	@PersistenceContext
	private EntityManager manager;

	@Override
	public boolean supports(Class<?> clazz) {
		return CadastroClienteRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors()) {
			return;
		}

		CadastroClienteRequest request = (CadastroClienteRequest) target;

		if (!request.temEstado()) {
			return;
		}

		Optional<Estado> estado = Optional.ofNullable(manager.find(Estado.class, request.getIdEstado()));
		if(estado.isEmpty()) {
			errors.rejectValue("idEstado", null, "Estado não cadastrado no banco de dados");
			return;
		}
		
		Pais pais = manager.find(Pais.class, request.getIdPais());
		if(!estado.get().pertenceAPais(pais)) {
			errors.rejectValue("idEstado", null, "Estado não pertece ao pais selecionado");
		}
	}
}
