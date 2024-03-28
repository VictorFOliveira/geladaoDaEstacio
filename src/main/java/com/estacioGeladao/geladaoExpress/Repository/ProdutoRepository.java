package com.estacioGeladao.geladaoExpress.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.estacioGeladao.geladaoExpress.entities.Produto;

import jakarta.transaction.Transactional;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	@Transactional
    @Modifying
    @Query("UPDATE Produto p SET p.quantidade = ?2 WHERE p.id = ?1")
    void atualizarEstoque(Long id, Integer quantidade);


}
