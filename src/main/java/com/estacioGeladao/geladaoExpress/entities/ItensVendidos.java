package com.estacioGeladao.geladaoExpress.entities;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_itens_vendidos")
public class ItensVendidos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Produto produto;

	private Integer quantidade;

	@ManyToOne
	@JoinColumn(name = "venda_id")
    @JsonBackReference
	private Venda venda;

	public ItensVendidos() {
	}

	public ItensVendidos(Long id, Produto produto, Integer quantidade, Venda venda) {
		this.id = id;
		this.produto = produto;
		this.quantidade = quantidade;
		this.venda = venda;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItensVendidos other = (ItensVendidos) obj;
		return Objects.equals(id, other.id);
	}

	public void descontarDoEstoque() {
		if (produto != null && quantidade != null && quantidade > 0) {
			int novaQuantidade = produto.getQuantidade() - quantidade;
			if (novaQuantidade >= 0) {
				produto.setQuantidade(novaQuantidade);
			} else {
				throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getNome());
			}
		} else {
			throw new RuntimeException("Produto ou quantidade inválidos.");
		}
	}
}
