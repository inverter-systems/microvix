package com.topinternacional.linx.bean.nfe;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Card {

	private String tpIntegra;
	@XStreamAlias("CNPJ")
	private String cnpj;
	private String tBand; // 01=Visa, 02=Mastercard, 03=American Express, 04=Sorocred, 05=Diners Club, 06=Elo, 07=Hipercard, 08=Aura, 09=Cabal, 99=Outros (Atualizado na NT2016.002)
	private String cAut;
	
	public String getTpIntegra() {
		return tpIntegra;
	}
	public void setTpIntegra(String tpIntegra) {
		this.tpIntegra = tpIntegra;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String gettBand() {
		return tBand;
	}
	public void settBand(String tBand) {
		this.tBand = tBand;
	}
	public String getcAut() {
		return cAut;
	}
	public void setcAut(String cAut) {
		this.cAut = cAut;
	}
	
}
