package br.com.zup.casadocodigo.crialivros;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.com.zup.casadocodigo.criaautor.Autor;
import br.com.zup.casadocodigo.criacategoria.Categoria;

public class NovoLivroRequest {

	@NotBlank
	private String titulo;
	@NotBlank
	@Size(max = 500)
	private String resumo;
	@NotBlank
	private String sumario;
	@NonNull
	@Min(20)
	private BigDecimal preco;
	@Min(100)
	private int numeroPaginas;
	@NotBlank
	private String isbn;
	@Future
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
	private LocalDate dataPublicacao;
	@NotNull
	@ExistsId(domainClass = Autor.class, fieldName = "id")
	private Long idAutor;
	@ExistsId(domainClass = Autor.class, fieldName = "id")
	@NotNull
	private Long idCategoria;
	
	public NovoLivroRequest(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo, @NotBlank String sumario,
			@Min(20) BigDecimal preco, @Min(100) int numeroPaginas, @NotBlank String isbn,
			@NotNull Long idAutor, @NotNull Long idCategoria) {
		this.titulo = titulo;
		this.resumo = resumo;
		this.sumario = sumario;
		this.preco = preco;
		this.numeroPaginas = numeroPaginas;
		this.isbn = isbn;
		this.idAutor = idAutor;
		this.idCategoria = idCategoria;
	}

	public void setDataPublicacao(LocalDate dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public Livro toModel(EntityManager manager) {
		@NotNull Autor autor = manager.find(Autor.class, this.idAutor);
		@NotNull Categoria categoria = manager.find(Categoria.class, this.idCategoria);

		return new Livro(titulo, resumo, sumario, preco, numeroPaginas, isbn, dataPublicacao, categoria, autor);
	}

}
