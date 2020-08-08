package br.com.projeto.carros.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarroRepository extends CrudRepository<Carro, Long> {
	
	List<Carro> findByTipo (String tipo);

}
