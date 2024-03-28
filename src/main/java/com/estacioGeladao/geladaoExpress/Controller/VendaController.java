package com.estacioGeladao.geladaoExpress.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estacioGeladao.geladaoExpress.Exceptions.VendaException;
import com.estacioGeladao.geladaoExpress.Service.VendaService;
import com.estacioGeladao.geladaoExpress.entities.Venda;

@RestController
@RequestMapping("/vendas")
public class VendaController {

	@Autowired
	private VendaService service;

	@PostMapping("/registro")
	public ResponseEntity<Venda> registrarVenda(@RequestBody Venda venda) {
		try {
			Venda vendaRegistrada = service.salvarVenda(venda);
			if (vendaRegistrada != null) {
				System.out.println("Venda registrada!");
				return ResponseEntity.status(HttpStatus.CREATED).body(vendaRegistrada);
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		} catch (Exception e) {
			e.getCause();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/listarVendas")
	public List<Venda> listarVendas() {
		return service.listarTodas();
	}

	@GetMapping("/vendaId/{id}")
	public Venda buscarPorId(@PathVariable Long id) {
		Venda vendaId = service.buscarVendaPorId(id);
		if (vendaId == null) {
			throw new VendaException(VendaException.VENDA_NAO_ENCONTRADA);
		}
		return vendaId;
	}
	
	@DeleteMapping("/deletarId/{id}")
	public ResponseEntity<String> deletarVendaPeloId(@PathVariable Long id) {
	    Venda vendaId = service.buscarVendaPorId(id);
	    service.deletarVenda(vendaId.getId());
	    String mensagem = "Venda com ID " + id + " foi deletada com sucesso.";
	    return ResponseEntity.ok().body(mensagem);
	}

}
