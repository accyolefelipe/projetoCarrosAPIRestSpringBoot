package br.com.projeto.carros.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {

	@GetMapping
	public String get() {
		return "Get API CARROS";
	}
	
	@PostMapping
	public String post() {
		return "Post - Jaspion é melhor que Jiraya";
	}
	
	@PutMapping
	public String put() {
		return "Put - Jaspion é melhor que Jiraya";
	}
	
	@DeleteMapping
	public String delete() {
		return "Delete - Jaspion é melhor que Jiraya";
	}

}
