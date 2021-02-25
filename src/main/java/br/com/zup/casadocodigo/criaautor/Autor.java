package br.com.zup.casadocodigo.criaautor;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Autor {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = true)
	private @NotBlank String nome; 
	@Column(unique = true, nullable = true)
	private @NotBlank @Email String email;
	@Column(nullable = true, length = 400)
	private @NotBlank @Size(max = 400) String descricao;
	@Column(nullable = true)
	private LocalDateTime instante = LocalDateTime.now();

	@Deprecated
	public Autor() {
		
	}
	
	public Autor(@NotBlank String nome, @NotBlank @Email String email, @NotBlank @Size(max = 400) String descricao) {
		this.nome = nome;
		this.email = email;
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Autor [id=" + id + ", nome=" + nome + ", email=" + email + ", descricao=" + descricao + ", instante="
				+ instante + "]";
	}

	public String getDescricao() {
		return descricao;
	}

	public String getNome() {
		return nome;
	}

	
}
