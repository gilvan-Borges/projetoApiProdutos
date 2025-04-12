package br.com.cotiinformatica.dtos;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProdutoRequestDto {

	private String nome;
	private Double preco;
	private Integer quantidade;
	private UUID categoriaId;
}
