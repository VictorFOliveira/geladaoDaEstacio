package com.estacioGeladao.geladaoExpress.entities;

import java.io.Serializable;
import java.util.Objects;

import com.estacioGeladao.geladaoExpress.enums.TipoDeAcesso;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_usuarios")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String email;
	private String senha;
	private String cpf;
	private TipoDeAcesso acesso;

	public Usuario() {
	}

	public Usuario(Long id, String email, String senha, String cpf, int acesso) {
	    super();
	    this.id = id;
	    this.email = email;
	    this.senha = senha;
	    this.cpf = cpf;
		this.acesso = TipoDeAcesso.getByValor(acesso);
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public TipoDeAcesso getAcesso() {
	    return acesso;
	}

	public void setAcesso(TipoDeAcesso acesso) {
	    this.acesso = acesso;
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
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}

}
