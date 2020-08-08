package br.com.projeto.carros.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarroService {

	@Autowired
	CarroRepository carroRepository;

	public Iterable<Carro> getCarros() {
		return carroRepository.findAll();
	}

	public Optional<Carro> getCarroById(Long id) {
		return carroRepository.findById(id);
	}

	public List<Carro> getCarrosByTipo(String tipo) {
		return carroRepository.findByTipo(tipo);
	}

	public Carro insert(Carro carro) {
		return carroRepository.save(carro);
	}

	public Carro update(Carro carro, Long id) {
		Optional<Carro> optional = getCarroById(id);

		if (optional.isPresent()) {
			Carro carroBanco = optional.get();

			carroBanco.setNome(carro.getNome());
			carroBanco.setTipo(carro.getTipo());
			System.out.println("Carro id: " + carroBanco.getId());

			carroRepository.save(carroBanco);

			return carroBanco;

		} else {
			throw new RuntimeException("Não Foi Possivel Atualizar o Registro");
		}

	}

	public void delete(Long id) {
		Optional<Carro> carro = getCarroById(id);

		if (carro.isPresent()) {
		carroRepository.deleteById(id);
		
		}
	}

	

}
