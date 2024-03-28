package com.estacioGeladao.geladaoExpress.enums;

import java.io.Serializable;

public enum TipoDeAcesso implements Serializable {
	
    Vendedor(0),
    Administrador(1);

    private int valor;

    TipoDeAcesso(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public static TipoDeAcesso getByValor(int valor) {
        for (TipoDeAcesso tipo : values()) {
            if (tipo.getValor() == valor) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de acesso inválido: " + valor);
    }
}
