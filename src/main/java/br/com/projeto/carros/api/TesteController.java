package br.com.projeto.carros.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class TesteController {

	@GetMapping
	public String get() {
		return "TESTE -Get - Jaspion é melhor que Jiraya";
	}

	@GetMapping("/login") //http://localhost:8080/teste/login?login=felipe&senha=123//
	public String login(@RequestParam("login") String login, @RequestParam("senha") String senha) {
		return "Login: " + login + " " + "Senha: " + senha;
	}
	
	@PostMapping("/login") 	
	public String logiin(@RequestParam("login") String login, @RequestParam("senha") String senha) {
		return "Login: " + login + " " + "Senha: " + senha;
	}
	
	@GetMapping("/login/{login}/senha/{senha}") //http://localhost:8080/teste/login/drauzio/senha/vareula//
	public String loginPath(@PathVariable("login") String login, @PathVariable("senha") String senha) {
		return "Login: " + login + " " + "Senha: " + senha;
	}
	
	@GetMapping("/carros/{id}") //http://localhost:8080/teste/carros/1 //
	public String getCarroById (@PathVariable("id") Long id) {
		return "Carro de id: " + id;
	}
	
	@GetMapping("/carros/tipo/{tipo}") //http://localhost:8080/teste/carros/tipo/esporte//
	public String getCarrosByTipo (@PathVariable("tipo") String tipo) {
		return "Lista de carros do tipo: " + tipo;
	}
	
	@GetMapping("carros/cor/{cor}") //http://localhost:8080/teste/carros/cor/vermelho//
	public String getCarrosByCor (@PathVariable("cor") String cor) {
		return "carros de cor : " + cor;
	}

	@PostMapping
	public String post() {
		return "TESTE -Post - Jaspion é melhor que Jiraya";
	}

	@PutMapping
	public String put() {
		return "TESTE -Put - Jaspion é melhor que Jiraya";
	}

	@DeleteMapping
	public String delete() {
		return "TESTE -Delete - Jaspion é melhor que Jiraya";
	}

}
