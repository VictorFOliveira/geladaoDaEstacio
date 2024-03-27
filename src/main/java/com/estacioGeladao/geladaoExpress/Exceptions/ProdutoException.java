package com.estacioGeladao.geladaoExpress.Exceptions;

public class ProdutoException extends RuntimeException {
    
	private static final long serialVersionUID = 1L;
	
	public static final String PRODUTO_NAO_ENCONTRADO = "O produto não foi encontrado";
    public static final String PRODUTO_NAO_EXISTE = "ERROR...O produto não existe";
    public static final String SEM_PRODUTOS_CADASTRADOS = " Não existem produtos cadastrados";

    public ProdutoException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
