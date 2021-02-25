package br.com.zup.casadocodigo.paisestado;

import javax.validation.constraints.NotBlank;

import br.com.zup.casadocodigo.validacao.UniqueValue;

public class NovoPaisRequest {

	@NotBlank
	@UniqueValue(domainClass = Pais.class, fieldName = "nome")
	private String nome;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome.toUpperCase();
	}

	public Pais toModel() {
		return new Pais(this.nome);
	}
}
