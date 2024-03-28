package com.estacioGeladao.geladaoExpress.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacioGeladao.geladaoExpress.Exceptions.VendaException;
import com.estacioGeladao.geladaoExpress.Repository.ItensVendidosRepository;
import com.estacioGeladao.geladaoExpress.Repository.ProdutoRepository;
import com.estacioGeladao.geladaoExpress.Repository.UsuarioRepository;
import com.estacioGeladao.geladaoExpress.Repository.VendaRepository;
import com.estacioGeladao.geladaoExpress.entities.ItensVendidos;
import com.estacioGeladao.geladaoExpress.entities.Produto;
import com.estacioGeladao.geladaoExpress.entities.Usuario;
import com.estacioGeladao.geladaoExpress.entities.Venda;

import jakarta.transaction.Transactional;

@Service
public class VendaService {

	@Autowired
	private VendaRepository vendaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	ItensVendidosRepository itensVendidosRepository;

	@Autowired
	ProdutoRepository produtoRepository;

	@Transactional
	public Venda salvarVenda(Venda venda) {
		try {

			Usuario vendedor = usuarioRepository.findById(venda.getVendedor().getId())
					.orElseThrow(() -> new VendaException("Vendedor não encontrado"));
			// Define o vendedor na venda
			venda.setVendedor(vendedor);

			Double valorTotal = 0.0; // Inicializa o valor total como 0.0
			for (ItensVendidos item : venda.getItensVendidos()) {
				Produto produto = produtoRepository.findById(item.getProduto().getId())
						.orElseThrow(() -> new VendaException("Produto não encontrado"));
				item.setProduto(produto);

				int novaQuantidade = produto.getQuantidade() - item.getQuantidade();
				produto.setQuantidade(novaQuantidade);
				produtoRepository.save(produto);

				// Calcula o valor total do item e adiciona ao valor total da venda
				valorTotal += produto.getPrecoUnitario() * item.getQuantidade();
				venda.setValorTotal(valorTotal);
			}

			if (venda.getVendedor() == null || venda.getVendedor().getId() == null) {
				throw new VendaException("ID do vendedor não foi fornecido");
			}

			for (ItensVendidos item : venda.getItensVendidos()) {
				Produto produto = produtoRepository.findById(item.getProduto().getId())
						.orElseThrow(() -> new VendaException("Produto não encontrado"));
				item.setProduto(produto);
				item.setVenda(venda);
			}

			if (venda.getVendedor() == null || venda.getVendedor().getId() == null) {
				throw new VendaException("ID do vendedor não foi fornecido");
			}

			return vendaRepository.save(venda);
		} catch (VendaException e) {
			throw e;
		} catch (Exception e) {
			throw new VendaException("Não foi possível salvar a venda");
		}
	}

	public List<Venda> listarTodas() {
		List<Venda> listaDeVendas = vendaRepository.findAll();

		if (listaDeVendas.isEmpty() || listaDeVendas == null) {
			throw new VendaException(VendaException.NENHUMA_VENDA_ENCONTRADA);
		}

		return listaDeVendas;
	}

	public Venda buscarVendaPorId(Long id) {
		Venda venda = vendaRepository.findById(id).orElse(null);
		if (venda == null) {
			throw new VendaException(VendaException.VENDA_NAO_ENCONTRADA);
		}

		return venda;
	}

	public void deletarVenda(Long id) {
		Venda venda = vendaRepository.findById(id).orElse(null);
		if (venda == null) {
			throw new VendaException(VendaException.VENDA_NAO_ENCONTRADA);
		}
		 vendaRepository.deleteById(id);
	}

}
