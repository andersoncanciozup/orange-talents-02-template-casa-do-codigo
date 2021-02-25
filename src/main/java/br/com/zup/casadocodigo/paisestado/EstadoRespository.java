package br.com.zup.casadocodigo.paisestado;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRespository extends CrudRepository<Estado, Long>{

	Optional<Estado> findByNome(String nome);

	boolean existsByNomeAndPaisId(String nome, Long id);
}
