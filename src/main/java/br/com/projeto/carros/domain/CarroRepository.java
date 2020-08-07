package br.com.projeto.carros.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarroRepository extends CrudRepository<Carro, Long> {
	
	Iterable<Carro> findByTipo (String tipo);

}
