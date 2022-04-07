package com.topinternacional.linx.exception;

public class SemNotasParaImportarException extends Exception {

	private static final long serialVersionUID = 3409614155561557256L;

	public SemNotasParaImportarException() {
		super("Não existe notas para importar com os parametros informados");
	}
}
