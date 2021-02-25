package br.com.zup.casadocodigo.pagamento;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.casadocodigo.paisestado.Estado;
import br.com.zup.casadocodigo.paisestado.Pais;
import br.com.zup.casadocodigo.validacao.ExistsId;
import br.com.zup.casadocodigo.validacao.UniqueValue;

public class CadastroClienteRequest {

	@NotBlank
	@Email
	@UniqueValue(domainClass = Cliente.class, fieldName = "email")
	private String email;
	@NotBlank
	private String nome;
	@NotBlank
	private String sobreNome;
	@NotBlank
	@CPFouCNPJ
	@UniqueValue(domainClass = Cliente.class, fieldName = "documento")
	private String documento;
	@NotBlank
	private String endereco;
	@NotBlank
	private String complemento;
	@NotBlank
	private String cidade;
	@NotNull
	@ExistsId(domainClass = Pais.class, fieldName = "id")
	private Long idPais;
	private Long idEstado;
	@NotBlank
	private String telefone;
	@NotBlank
	private String cep;

	public CadastroClienteRequest(@NotBlank @Email String email, @NotBlank String nome, @NotBlank String sobreNome,
			@NotBlank String documento, @NotBlank String endereco, @NotBlank String complemento,
			@NotBlank String cidade, @NotNull Long idPais, @NotNull Long idEstado, @NotBlank String telefone,
			@NotBlank String cep) {
		super();
		this.email = email;
		this.nome = nome;
		this.sobreNome = sobreNome;
		this.documento = documento;
		this.endereco = endereco;
		this.complemento = complemento;
		this.cidade = cidade;
		this.idPais = idPais;
		this.idEstado = idEstado;
		this.telefone = telefone;
		this.cep = cep;
	}

	@Override
	public String toString() {
		return "CadastroClienteRequest [email=" + email + ", nome=" + nome + ", sobreNome=" + sobreNome + ", documento="
				+ documento + ", endereco=" + endereco + ", complemento=" + complemento + ", cidade=" + cidade
				+ ", idPais=" + idPais + ", idEstado=" + idEstado + ", telefone=" + telefone + ", cep=" + cep + "]";
	}

	public Cliente toModel(EntityManager manager) {

		Pais pais = manager.find(Pais.class, idPais);
		
		Cliente cliente = new Cliente(email, nome, sobreNome, documento, endereco, complemento, cidade, pais, telefone,
				cep);
		
		if (idEstado != null) {
			cliente.setEstado(manager.find(Estado.class, idEstado));
		}
		
		return cliente;
	}

	public boolean temEstado() {
		return Optional.ofNullable(idEstado).isPresent();
	}
	
	public Long getIdPais() {
		return idPais;
	}

	public Long getIdEstado() {
		return idEstado;
	}

}
