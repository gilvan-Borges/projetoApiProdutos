package br.com.cotiinformatica.controllers;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.cotiinformatica.dtos.CategoriaMediaPrecoDto;
import br.com.cotiinformatica.dtos.CategoriaQtdProdutosDto;
import br.com.cotiinformatica.repositories.CategoriaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
@RestController
@RequestMapping("/api/dashboard")
@Tag(name = "Dashboard", description = "API para consulta de dashboards")
public class DashboardController {
	@Operation(summary = "Somatório da quantidade de produtos por categoria")
	@GetMapping("categoria-qtdprodutos")
	public List<CategoriaQtdProdutosDto> getCategoriaQtdProdutos() throws Exception {
		var categoriaRepository = new CategoriaRepository();
		return categoriaRepository.groupByCategoriaQtdProdutos();
	}
	
	@Operation(summary = "Média de preço de produtos por categoria")
	@GetMapping("categoria-mediapreco")
	public List<CategoriaMediaPrecoDto> getCategoriaMediaPrecoProdutos() throws Exception {
		var categoriaRepository = new CategoriaRepository();
		return categoriaRepository.groupByCategoriaMediaPreco();
	}
	
}



