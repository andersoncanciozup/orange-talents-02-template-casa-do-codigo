package br.com.zup.casadocodigo.paisestado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProibeEstadoDuplicadoParaMesmoPaisValidator implements Validator {

	@Autowired
	private EstadoRespository repository;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return NovoEstadoRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors()) {
			return;
		}
		
		NovoEstadoRequest request = (NovoEstadoRequest) target;
		boolean ExisteEstadoNoMesmoPais = repository.existsByNomeAndPaisId(request.getNome(), request.getIdPais());
		
		if(ExisteEstadoNoMesmoPais) {
			errors.rejectValue("IdPais", null, "já existe um estado com o mesmo nome para esse pais");
		}
		
	}

}
