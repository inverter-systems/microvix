package com.topinternacional.linx.exception;

public class ComunicacaoException extends Exception {

	private static final long serialVersionUID = -7943149476052217485L;

	public ComunicacaoException() {
		super("Não foi possivel estabelece comunicação com um dos servidores");
	}
}
