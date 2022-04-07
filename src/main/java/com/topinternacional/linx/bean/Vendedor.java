package com.topinternacional.linx.bean;

import java.util.List;

import com.topinternacional.linx.services.util.Util;

public class Vendedor {
	
	private Integer id; // Cod vendedor microvix
	private String nome;
	private String cpf;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public static Vendedor getVendedor(Response response) {
		Vendedor ven = new Vendedor();
		
		List<String> vendedor = Util.getRegistrosXML(response);
		
		String[] col =  Util.getColunasXML(vendedor.get(0));
		
		ven.setId(Integer.valueOf(col[2]));
		ven.setNome(col[3]);
		ven.setCpf(col[15]);
			
		return ven;
	}
	
	public String getVendedorFmt() {
		return nome+" ("+cpf+")";
	}
}
