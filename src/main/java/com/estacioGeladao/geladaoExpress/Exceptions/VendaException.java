package com.estacioGeladao.geladaoExpress.Exceptions;

public class VendaException extends RuntimeException {
    
	private static final long serialVersionUID = 1L;
	
	public static final String VENDA_NAO_ENCONTRADA = "A venda não foi encontrada";
    public static final String VENDA_NAO_EXISTE = "ERROR...A venda não existe";
    public static final String NENHUMA_VENDA_ENCONTRADA = "Nenhuma venda foi encontrada";


    public VendaException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
