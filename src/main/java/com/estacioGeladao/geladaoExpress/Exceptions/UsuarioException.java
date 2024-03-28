package com.estacioGeladao.geladaoExpress.Exceptions;

public class UsuarioException extends RuntimeException {
    
	private static final long serialVersionUID = 1L;
	
	public static final String USUARIO_NAO_ENCONTRADO = "O usuario não foi encontrado";
    public static final String USUARIO_NAO_EXISTE = "ERROR...O usuario não existe";
    public static final String SEM_USUARIOS_CADASTRADOS = " Não existem usuarios cadastrados";

    public UsuarioException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
