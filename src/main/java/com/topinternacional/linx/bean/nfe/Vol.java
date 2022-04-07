package com.topinternacional.linx.bean.nfe;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("vol")
public class Vol {

	private String qVol;
	private String pesoL;
	private String pesoB;
	
	public String getqVol() {
		return qVol;
	}
	public void setqVol(String qVol) {
		this.qVol = qVol;
	}
	public String getPesoL() {
		return pesoL;
	}
	public void setPesoL(String pesoL) {
		this.pesoL = pesoL;
	}
	public String getPesoB() {
		return pesoB;
	}
	public void setPesoB(String pesoB) {
		this.pesoB = pesoB;
	}
	
}
