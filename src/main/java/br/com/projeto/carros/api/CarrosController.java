package br.com.projeto.carros.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.carros.domain.Carro;
import br.com.projeto.carros.domain.CarroService;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {
	
	@Autowired
	private CarroService carroService;
	
	@GetMapping()
	public Iterable<Carro> getCarros(){
		return carroService.getCarros();
	}
	
	@GetMapping("/{id}")
	public Optional<Carro> getCarroById(@PathVariable("id") Long id) {
		return carroService.getCarroById(id);
	}
	
	@GetMapping("/tipo/{tipo}")
	public Iterable<Carro> getCarroByTipo (@PathVariable("tipo") String tipo) {
		return carroService.getCarrosByTipo(tipo);
	}
	
	@PostMapping
	public String post(@RequestBody Carro carro) {
		Carro c = carroService.insert(carro);
		return "Carro Salvo com Sucesso " + c.getId();
	}
	
	@PutMapping("/{id}")
	public String put(@PathVariable ("id") Long id ,@RequestBody Carro carro) {
		Carro c = carroService.update(carro , id);
		return "Carro atualizado com Sucesso " +  c.getId();
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		carroService.delete(id);
	}
	
	
	

}
