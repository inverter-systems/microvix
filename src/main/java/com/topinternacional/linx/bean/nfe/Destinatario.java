package com.topinternacional.linx.bean.nfe;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("dest")
public class Destinatario {
	
	@XStreamAlias("CNPJ")
	private String cnpj;
	@XStreamAlias("CPF")
	private String cpf;
	private Integer idEstrangeiro;
	private String xNome;
	private Endereco enderDest = new Endereco();
	private String indIEDest;
	@XStreamAlias("IE")
	private String ie;
	@XStreamAlias("ISUF")
	private String ISUF;
	@XStreamAlias("AM")
	private String IM;
	private String email;
	
	public Endereco getEnderDest() {
		return enderDest;
	}

	public void setEnderDest(Endereco enderDest) {
		this.enderDest = enderDest;
	}

	public String getIndIEDest() {
		return indIEDest;
	}

	public void setIndIEDest(String indIEDest) {
		this.indIEDest = indIEDest;
	}

	public String getIe() {
		return ie;
	}

	public void setIe(String ie) {
		this.ie = ie;
	}

	public String getISUF() {
		return ISUF;
	}

	public void setISUF(String iSUF) {
		ISUF = iSUF;
	}

	public String getIM() {
		return IM;
	}

	public void setIM(String iM) {
		IM = iM;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Integer getIdEstrangeiro() {
		return idEstrangeiro;
	}

	public void setIdEstrangeiro(Integer idEstrangeiro) {
		this.idEstrangeiro = idEstrangeiro;
	}

	public String getxNome() {
		return xNome;
	}

	public void setxNome(String xNome) {
		this.xNome = xNome;
	}

	public Endereco getEnderEmit() {
		return enderDest;
	}

	public void setEnderEmit(Endereco enderEmit) {
		this.enderDest = enderEmit;
	}
	
	
	

}
