package br.com.cotiinformatica.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.dtos.ProdutoRequestDto;
import br.com.cotiinformatica.entities.Categoria;
import br.com.cotiinformatica.entities.Produto;
import br.com.cotiinformatica.repositories.ProdutoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/produtos")
@Tag(name = "Produtos", description = "API para gerenciamento de produtos")
public class ProdutoController {

    @PostMapping
    @Operation(summary = "Criar um novo produto", description = "Endpoint para cadastrar um novo produto no sistema.")
    public String post(@RequestBody ProdutoRequestDto request) throws Exception {

    	var produto = new Produto(); //instanciando um objeto da classe produto
    	produto.setCategoria(new Categoria()); //instanciando a categoria contida no produto

    	//capturando as informações que o DTO trouxe para o nosso serviço
    	produto.setId(UUID.randomUUID()); //gerando um id para o produto
    	produto.setNome(request.getNome()); //capturando o nome do produto
    	produto.setPreco(request.getPreco()); //capturando o preço do produto
    	produto.setQuantidade(request.getQuantidade()); //capturando a quantidade do produto
    	produto.getCategoria().setId(request.getCategoriaId()); //capturando o id da categoria
    	
    	//gravando no banco de dados
    	var produtoRepository = new ProdutoRepository();
    	produtoRepository.create(produto);
    	
    	//retornando mensagem de sucesso
    	return "Produto cadastrado com sucesso.";
    }

    @PutMapping
    @Operation(summary = "Atualizar um produto", description = "Endpoint para atualizar um produto existente no sistema.")
    public void put() throws Exception {
        // TODO: Implementar atualização de produto
    }

    @DeleteMapping
    @Operation(summary = "Excluir um produto", description = "Endpoint para remover um produto do sistema.")
    public void delete() throws Exception {
        // TODO: Implementar exclusão de produto
    }

    @GetMapping
    @Operation(summary = "Listar todos os produtos", description = "Endpoint para listar todos os produtos cadastrados no sistema.")
    public List<Produto> getAll(@RequestParam String nome) throws Exception {

    	//consultar e retornar os produtos do banco de dados
    	var produtoRepository = new ProdutoRepository();
    	return produtoRepository.findByNome(nome);
    }
}













