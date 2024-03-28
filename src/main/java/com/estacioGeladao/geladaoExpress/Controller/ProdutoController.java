package com.estacioGeladao.geladaoExpress.Controller;

import java.util.List;

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

import com.estacioGeladao.geladaoExpress.Service.ProdutoService;
import com.estacioGeladao.geladaoExpress.entities.Produto;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService service;

	@PostMapping("/AdicionarProduto")
	public Produto addProduto(@RequestBody Produto produto) {
		return service.saveProduto(produto);
	}

	@GetMapping("/listarProdutos")
	public List<Produto> getProdutos() {
		return service.findAll();
	}

	@GetMapping("/produto/{id}")
	public Produto getProdutoById(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Produto> updateProduto(@PathVariable Long id, @RequestBody Produto produtoAtualizado) {
	    Produto produto = service.updateProduto(id, produtoAtualizado);
	    return ResponseEntity.ok().body(produto);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletarProduto(@PathVariable Long id) {
	    service.deleteProduto(id);
	    return ResponseEntity.ok("Produto Deletado");
	}
	
}
