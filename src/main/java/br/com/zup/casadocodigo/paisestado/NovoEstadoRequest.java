package br.com.zup.casadocodigo.paisestado;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.casadocodigo.validacao.ExistsId;

public class NovoEstadoRequest {


	@NotBlank
	private String nome;
	@NotNull
	@ExistsId(domainClass = Pais.class, fieldName = "id")
	private Long idPais;

	public NovoEstadoRequest(@NotBlank String nome, @NotNull Long idPais) {
		super();
		this.nome = nome;
		this.idPais = idPais;
	}

	public Estado toModel(EntityManager manager) {
		Pais pais = manager.find(Pais.class, this.idPais);
		return new Estado(this.nome, pais);
	}

	public Long getIdPais() {
		return idPais;
	}

	public String getNome() {
		return nome;
	}
	
	
	
}
