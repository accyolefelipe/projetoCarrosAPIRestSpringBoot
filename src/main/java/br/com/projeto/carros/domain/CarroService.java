package br.com.projeto.carros.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.projeto.carros.domain.dto.CarroDTO;

@Service
public class CarroService {

	@Autowired
	CarroRepository carroRepository;

	public List<CarroDTO> getCarros() {
		List<Carro> carros = carroRepository.findAll();
		List<CarroDTO> list = new ArrayList<>();

		for (Carro c : carros) {
			list.add(CarroDTO.create(c));
		}
		return list;

		// return carroRepository.findAll() = carros.stream().map(CarroDTO::create).collect(Collectors.toList());
	}

	public Optional<CarroDTO> getCarroById(Long id) {
		return carroRepository.findById(id).map(CarroDTO::create);

		/*Optional<Carro> carro = carroRepository.findById(id);
		if (carro.isPresent()) {
			return Optional.of(CarroDTO.create(carro.get()));
		} else {
			return null;
		}*/
	}

	public List<CarroDTO> getCarrosByTipo(String tipo) {
		return carroRepository.findByTipo(tipo).stream().map(CarroDTO::create).collect(Collectors.toList());
	}

	public CarroDTO insert(Carro carro) {
		Assert.isNull(carro.getId(),"Não foi possível atualizar o registro");
		
		return CarroDTO.create(carroRepository.save(carro));
	}
	

	public CarroDTO update(Carro carro, Long id) {
		Assert.notNull(id,"Não foi possível atualizar o registro");
		
		Optional<Carro> optional = carroRepository.findById(id);

		if (optional.isPresent()) {
			Carro carroBanco = optional.get();

			carroBanco.setNome(carro.getNome());
			carroBanco.setTipo(carro.getTipo());
			System.out.println("Carro id: " + carroBanco.getId());

			carroRepository.save(carroBanco);
			return CarroDTO.create(carroBanco);

		} else {
			return null;
			//throw new RuntimeException("Não Foi Possivel Atualizar o Registro");
		}

	}

	public boolean delete(Long id) {
		Optional<CarroDTO> carro = getCarroById(id);

		if (carro.isPresent()) {
			carroRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
