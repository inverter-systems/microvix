package com.topinternacional.linx.api;

import com.topinternacional.linx.enun.Status;

public abstract class BasicController {
	
	protected static Status status = Status.AGUARDADO;
	protected static float andamento = 0;
	protected static String mensagem = "";
	
	public static void setStatus(Status sta, float num) {
		status = sta;
		andamento = num;
	}
	
	public static void setStatus(Status sta, float num, String msg) {
		status = sta;
		andamento = num;
		mensagem = msg;
	}
	
	public void ajustaStatus() {
		if (!status.equals(Status.AGUARDADO)) {
			status = Status.AGUARDADO;
			mensagem = "";
		}
	}
	
}
