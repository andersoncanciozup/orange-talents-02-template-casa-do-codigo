package br.com.zup.casadocodigo.criacategoria;

import javax.validation.constraints.NotBlank;

import br.com.zup.casadocodigo.validacao.UniqueValue;

public class NovaCategoriaRequest {
	
	@NotBlank
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome")
	private String nome;
	
	public NovaCategoriaRequest() {
		
	}
	
	public NovaCategoriaRequest(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
	
	public Categoria toModel() {
		return new Categoria(this.nome);
	}
}
