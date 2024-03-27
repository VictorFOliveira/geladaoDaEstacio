package com.estacioGeladao.geladaoExpress.enums;

public enum TipoDeAcesso {
	
	Vendedor(1), 
	Administrador(2);
	
	private int valor;
	
	TipoDeAcesso(int valor){
		this.valor = valor;
	}
	
	
	public int getValor() {
		return valor;
	}
	
}
