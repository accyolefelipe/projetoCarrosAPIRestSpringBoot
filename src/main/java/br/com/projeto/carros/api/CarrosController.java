package br.com.projeto.carros.api;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.projeto.carros.domain.Carro;
import br.com.projeto.carros.domain.CarroService;
import br.com.projeto.carros.domain.dto.CarroDTO;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {

	@Autowired
	private CarroService carroService;

	@GetMapping()
	public ResponseEntity<List<CarroDTO>> getCarros() {
		return ResponseEntity.ok(carroService.getCarros());
		// return new ResponseEntity<>(carroService.getCarros(), HttpStatus.OK);;
	}

	@GetMapping("/{id}")
	public ResponseEntity<CarroDTO> getCarroById(@PathVariable("id") Long id) {
		Optional<CarroDTO> carro = carroService.getCarroById(id);

		if (carro.isPresent()) {
			return ResponseEntity.ok(carro.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<List<CarroDTO>> getCarroByTipo(@PathVariable("tipo") String tipo) {
		List<CarroDTO> carros = carroService.getCarrosByTipo(tipo);

		if (carros.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(carros);
		}
	}

	@PostMapping
	public ResponseEntity post(@RequestBody Carro carro) {

		try {
			CarroDTO c = carroService.insert(carro);

			URI location = getUri(c);
			return ResponseEntity.created(location).build();

		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}

	private URI getUri(CarroDTO c) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(c.getId()).toUri();
	}

	@PutMapping("/{id}")
	public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Carro carro) {
		CarroDTO c = carroService.update(carro, id);

		return c != null ? ResponseEntity.ok(c) :
			ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		boolean ok = carroService.delete(id);
		
		return ok  ? ResponseEntity.ok().build() :
			ResponseEntity.notFound().build();
	}
}
