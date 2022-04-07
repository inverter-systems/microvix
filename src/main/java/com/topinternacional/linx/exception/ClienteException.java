package com.topinternacional.linx.exception;

public class ClienteException extends Exception {
	
	private static final long serialVersionUID = -7075155840381679551L;

	public ClienteException() {
		super("Não foi possivel encontrar ou criar o cliente no N&L");
	}
}
