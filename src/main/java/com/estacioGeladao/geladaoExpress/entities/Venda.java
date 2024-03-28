package com.estacioGeladao.geladaoExpress.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_venda")
public class Venda implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Date dataVenda = new Date();

	@ManyToOne
	@JoinColumn(name = "vendedor_id")
	private Usuario vendedor;

	@OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
    @JsonManagedReference
	private List<ItensVendidos> itensVendidos = new ArrayList<>();

	private Double valorTotal = 0.0;

	public Venda(Long id, Date dataVenda, Usuario vendedor, Double valorTotal) {
		this.id = id;
		this.dataVenda = dataVenda;
		this.vendedor = vendedor;
		this.valorTotal = valorTotal;
	}

	public Venda() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public Usuario getVendedor() {
		return vendedor;
	}

	public void setVendedor(Usuario vendedor) {
		this.vendedor = vendedor;
	}

	public List<ItensVendidos> getItensVendidos() {
		return itensVendidos;
	}

	public void setItensVendidos(List<ItensVendidos> itensVendidos) {
		this.itensVendidos = itensVendidos;
	}

	public Double getValorTotal() {
		Double valorTotal = 0.0;
		for (ItensVendidos itens : itensVendidos) {
			valorTotal += itens.getProduto().getPrecoUnitario() * itens.getQuantidade();
		}
		return valorTotal;
	}
	
	

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
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
		Venda other = (Venda) obj;
		return Objects.equals(id, other.id);
	}

	

}
