package com.topinternacional.linx.bean.nfe;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class IcmsAliq {
	
	private String orig;
	
	@XStreamAlias("CST")
	private String cst;
	
	@XStreamAlias("modBC")
	private String modBc;
	
	@XStreamAlias("vBC")
	private String vCb;
	
	@XStreamAlias("pICMS")
	private String pICMS;
	
	@XStreamAlias("vICMS")
	private String vIcms;
	
	@XStreamAlias("pRedBC")
	private String pRedBC;

	
	public String getpRedBC() {
		return pRedBC;
	}
	public void setpRedBC(String pRedBC) {
		this.pRedBC = pRedBC;
	}
	public String getOrig() {
		return orig;
	}
	public void setOrig(String orig) {
		this.orig = orig;
	}
	public String getCst() {
		return cst;
	}
	public void setCst(String cst) {
		this.cst = cst;
	}
	public String getModBc() {
		return modBc;
	}
	public void setModBc(String modBc) {
		this.modBc = modBc;
	}
	public String getvCb() {
		return vCb;
	}
	public void setvCb(String vCb) {
		this.vCb = vCb;
	}
	public String getpICMS() {
		return pICMS;
	}
	public void setpICMS(String pICMS) {
		this.pICMS = pICMS;
	}
	public String getvIcms() {
		return vIcms;
	}
	public void setvIcms(String vIcms) {
		this.vIcms = vIcms;
	}
}
