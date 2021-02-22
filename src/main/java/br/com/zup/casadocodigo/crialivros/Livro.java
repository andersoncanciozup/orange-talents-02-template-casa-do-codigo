package br.com.zup.casadocodigo.crialivros;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.zup.casadocodigo.criaautor.Autor;
import br.com.zup.casadocodigo.criacategoria.Categoria;

@Entity
public class Livro {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank String titulo;
	private @NotBlank @Size(max = 500) String resumo;
	private @NotBlank String sumario;
	private @Min(20) BigDecimal preco;
	private @Min(100) Integer numeroPaginas;
	private @NotBlank String isbn;
	private @Future LocalDate dataPublicacao;
	@ManyToOne
	private Categoria categoria;
	@ManyToOne
	private Autor autor;
	
//	public Livro(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo, @NotBlank String sumario,
//			@Min(20) BigDecimal preco, @Min(100) int numeroPaginas, @NotBlank String isbn,
//			@Future LocalDate dataPublicacao, Categoria categoria, Autor autor) {
//	}
//	
	@Deprecated
	public Livro() {
		
	}
	
	public Livro(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo, @NotBlank String sumario,
			@Min(20) BigDecimal preco, @Min(100) Integer numeroPaginas, @NotBlank String isbn,
			@Future LocalDate dataPublicacao, Categoria categoria, Autor autor) {
		super();
		this.titulo = titulo;
		this.resumo = resumo;
		this.sumario = sumario;
		this.preco = preco;
		this.numeroPaginas = numeroPaginas;
		this.isbn = isbn;
		this.dataPublicacao = dataPublicacao;
		this.categoria = categoria;
		this.autor = autor;
	}


	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	@Override
	public String toString() {
		return "Livro [id=" + id + ", titulo=" + titulo + ", resumo=" + resumo + ", sumario=" + sumario + ", preco="
				+ preco + ", numeroPaginas=" + numeroPaginas + ", isbn=" + isbn + ", dataPublicacao=" + dataPublicacao
				+ ", categoria=" + categoria + ", autor=" + autor + "]";
	}
	
}
