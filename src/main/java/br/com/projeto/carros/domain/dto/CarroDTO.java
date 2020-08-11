package br.com.projeto.carros.domain.dto;

import org.modelmapper.ModelMapper;

import br.com.projeto.carros.domain.Carro;
import lombok.Data;

@Data
public class CarroDTO {
	private Long id;
	private String nome;
	private String tipo;
	


	public static CarroDTO create(Carro c) {
		ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(c, CarroDTO.class);
		
	}

}