package br.com.projeto.carros;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.projeto.carros.domain.Carro;
import br.com.projeto.carros.domain.CarroService;
import br.com.projeto.carros.domain.dto.CarroDTO;

@SpringBootTest
class CarrosServiceTest {
	
	@Autowired
	CarroService carroService;

	@Test
	void testInserir() {
		Carro carro = new Carro();
		carro.setNome("Ferrari");
		carro.setTipo("esportivo");
		CarroDTO c = carroService.insert(carro);
		assertNotNull(c);
		
		Long id  = c.getId();
		assertNotNull(id);
		
		//BuscarObjeto
		Optional<CarroDTO> optional = carroService.getCarroById(id);
		assertTrue(optional.isPresent());
		
		c = optional.get();
		assertEquals("Ferrari", c.getNome());
		assertEquals("esportivo", c.getTipo());
		
		//DeletarObjeto
		carroService.delete(id);
		
		//VerificarExclusão
		assertFalse(carroService.getCarroById(id).isPresent());
	}
	
	@Test
	void testLista() {
		List<CarroDTO> carros = carroService.getCarros();
		assertEquals(30, carros.size());
	}
	
	@Test
	void testListaId() {
		Optional<CarroDTO> optional = carroService.getCarroById(11L);
		assertTrue(optional.isPresent());
		
		CarroDTO c = optional.get();
		assertEquals("Ferrari FF" , c.getNome());
	}
	
	@Test
	void testListaTipo() {
		assertEquals(10, carroService.getCarrosByTipo("classicos").size());
		assertEquals(10, carroService.getCarrosByTipo("esportivos").size());
		assertEquals(10, carroService.getCarrosByTipo("luxo").size());
		
		assertEquals(0, carroService.getCarrosByTipo("x").size());
	}
	
	

}
