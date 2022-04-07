package com.topinternacional.linx.bean.nfe;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class PisAliq {
	
	@XStreamAlias("CST")
	private String cst;

	@XStreamAlias("vBC")
	private String vCb;
	
	@XStreamAlias("pPIS")
	private String pPis;
	
	@XStreamAlias("vPIS")
	private String vPis;

	public String getCst() {
		return cst;
	}
	public void setCst(String cst) {
		this.cst = cst;
	}
	public String getvCb() {
		return vCb;
	}
	public void setvCb(String vCb) {
		this.vCb = vCb;
	}
	public String getpPis() {
		return pPis;
	}
	public void setpPis(String pPis) {
		this.pPis = pPis;
	}
	public String getvPis() {
		return vPis;
	}
	public void setvPis(String vPis) {
		this.vPis = vPis;
	}
}
