package com.topinternacional.linx.bean.nfe;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("emit")
public class Emitente {

	@XStreamAlias("CNPJ")
	private String cnpj;
	@XStreamAlias("CPF")
	private String cpf;
	private String xNome;
	private String xFant;
	
	private Endereco enderEmit = new Endereco();
	
	@XStreamAlias("IE")
	private String ie;
	@XStreamAlias("IEST")
	private String iest;
	@XStreamAlias("IM")
	private String im;
	@XStreamAlias("CNAE")
	private String cnae;
	@XStreamAlias("CRT")
	private String crt;
	
	public String getIm() {
		return im;
	}
	public void setIm(String im) {
		this.im = im;
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
	public String getxNome() {
		return xNome;
	}
	public void setxNome(String xNome) {
		this.xNome = xNome;
	}
	public String getxFant() {
		return xFant;
	}
	public void setxFant(String xFant) {
		this.xFant = xFant;
	}
	public Endereco getEnderEmit() {
		return enderEmit;
	}
	public void setEnderEmit(Endereco enderEmit) {
		this.enderEmit = enderEmit;
	}
	public String getIe() {
		return ie;
	}
	public void setIe(String ie) {
		this.ie = ie;
	}
	public String getIest() {
		return iest;
	}
	public void setIest(String iest) {
		this.iest = iest;
	}
	public String getCnae() {
		return cnae;
	}
	public void setCnae(String cnae) {
		this.cnae = cnae;
	}
	public String getCrt() {
		return crt;
	}
	public void setCrt(String crt) {
		this.crt = crt;
	}

}
