package com.estacioGeladao.geladaoExpress.Exceptions;

public class ProdutoPedidoException extends RuntimeException {
    
	private static final long serialVersionUID = 1L;
	
	public static final String PRODUTO_NAO_ENCONTRADO = "O produto não foi encontrado";
    public static final String PEDIDO_NAO_EXISTE = "ERROR...O pedido não existe";

    public ProdutoPedidoException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
