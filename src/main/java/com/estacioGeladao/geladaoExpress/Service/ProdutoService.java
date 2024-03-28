package com.estacioGeladao.geladaoExpress.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacioGeladao.geladaoExpress.Exceptions.ProdutoException;
import com.estacioGeladao.geladaoExpress.Repository.ProdutoRepository;
import com.estacioGeladao.geladaoExpress.entities.Produto;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	public Produto saveProduto(Produto produto) {
		return repository.save(produto);
	}
	
	public Produto findById(Long id) {
	    Optional<Produto> produtoEncontrado = repository.findById(id);
	    if (produtoEncontrado.isEmpty()) {
	        throw new ProdutoException(ProdutoException.PRODUTO_NAO_ENCONTRADO);
	    }
	    return produtoEncontrado.get();
	}
	
	public List<Produto> findAll() {
	    List<Produto> produtos = repository.findAll();
	    if (produtos.isEmpty()) {
	    	throw new ProdutoException(ProdutoException.SEM_PRODUTOS_CADASTRADOS);
	    }
	    return produtos;
	}
	
	public void deleteProduto(Long id) {
		Produto produtoEncontrado = findById(id);
		if(produtoEncontrado == null) {
	        throw new ProdutoException(ProdutoException.PRODUTO_NAO_ENCONTRADO);
		}
		 repository.deleteById(id);
	}
	
	public Produto updateProduto(Long id, Produto produtoAtualizado ) {
	    Produto produtoExist = findById(id);
	    if(produtoExist == null) {
	        throw new ProdutoException(ProdutoException.PRODUTO_NAO_ENCONTRADO);
	    }
	    
	    produtoExist.setNome(produtoAtualizado.getNome());
	    produtoExist.setMarca(produtoAtualizado.getMarca());
	    produtoExist.setPrecoUnitario(produtoAtualizado.getPrecoUnitario());
	    produtoExist.setDataValidade(produtoAtualizado.getDataValidade());
	    produtoExist.setQuantidade(produtoAtualizado.getQuantidade());
	   
	    
	    return repository.save(produtoExist);
	}
	
	 public void atualizarEstoque(Long id, Integer quantidade) {
	        Produto produto = findById(id);
	        if (produto != null) {
	            produto.setQuantidade(quantidade);
	            repository.save(produto);
	        }
	    }



}
