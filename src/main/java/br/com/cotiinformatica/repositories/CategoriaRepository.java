package br.com.cotiinformatica.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.cotiinformatica.dtos.CategoriaMediaPrecoDto;
import br.com.cotiinformatica.dtos.CategoriaQtdProdutosDto;
import br.com.cotiinformatica.entities.Categoria;
import br.com.cotiinformatica.factories.ConnectionFactory;

public class CategoriaRepository {

	private ConnectionFactory connectionFactory = new ConnectionFactory();

	public List<Categoria> findAll() throws Exception {

		var connection = connectionFactory.getConnection();

		var query = """
					SELECT
					    id, nome
					FROM
					    categoria
					ORDER BY
					    nome
				""";

		var statement = connection.prepareStatement(query);
		var result = statement.executeQuery();

		var lista = new ArrayList<Categoria>();

		while (result.next()) {

			var categoria = new Categoria();

			categoria.setId(UUID.fromString(result.getString("id")));
			categoria.setNome(result.getString("nome"));

			lista.add(categoria);
		}

		connection.close();
		return lista;
	}

	public List<CategoriaQtdProdutosDto> groupByCategoriaQtdProdutos() throws Exception {

		var connection = connectionFactory.getConnection();

		var query = """
					SELECT
						c.nome as CATEGORIA,
						SUM(p.quantidade) as QTDPRODUTOS
					FROM
						produto p
					INNER JOIN
						categoria c
					ON
						c.id = p.categoria_id
					GROUP BY
						c.nome

				""";

		var statement = connection.prepareStatement(query);
		var result = statement.executeQuery();

		var lista = new ArrayList<CategoriaQtdProdutosDto>();

		while (result.next()) {
			var dto = new CategoriaQtdProdutosDto();
			dto.setCategoria(result.getString("categoria"));
			dto.setQtdProdutos(result.getInt("qtdprodutos"));

			lista.add(dto);
		}

		connection.close();
		return lista;
	}

	public List<CategoriaMediaPrecoDto> groupByCategoriaMediaPreco() throws Exception {

		var connection = connectionFactory.getConnection();

		var query = """
				SELECT
					c.nome as CATEGORIA,
					ROUND(AVG(p.preco),2) as MEDIAPRECO
				FROM
					produto p
				INNER JOIN
					categoria c
				ON
					c.id = p.categoria_id
				GROUP BY
					c.nome


							""";

		var statement = connection.prepareStatement(query);
		var result = statement.executeQuery();

		var lista = new ArrayList<CategoriaMediaPrecoDto>();

		while (result.next()) {
			var dto = new CategoriaMediaPrecoDto();
			dto.setCategoria(result.getString("categoria"));
			dto.setMediaPreco(result.getDouble("mediapreco"));

			lista.add(dto);
		}

		connection.close();
		return lista;
	}
}
