package com.topinternacional.linx.bean.nfe;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Produto {

	private String cProd;
	@XStreamAlias("cEAN")
	private String cEan;
	private String xProd;
	@XStreamAlias("NCM")
	private String ncm;
	@XStreamAlias("NVE")
	private String nve;
	@XStreamAlias("CEST")
	private String cest;
	private String indEscala;
	@XStreamAlias("CNPJFab")
	private String cnpjFab;
	
	private String cBenef;
	
	@XStreamAlias("EXTIPI")
	private String extIpi;
	
	@XStreamAlias("CFOP")
	private Integer cfop;
	private String uCom;
	private String qCom;
	private String vUnCom;
	private String vProd;
	
	@XStreamAlias("cEANTrib")
	private String cEanTrib;
	private String uTrib;
	private String qTrib;
	private String vUnTrib;
	private String vFrete;
	private String vSeg;
	private String vDesc;
	private String vOutro;
	private String indTot;
	
	public String getNve() {
		return nve;
	}
	public void setNve(String nve) {
		this.nve = nve;
	}
	public String getCnpjFab() {
		return cnpjFab;
	}
	public void setCnpjFab(String cnpjFab) {
		this.cnpjFab = cnpjFab;
	}
	public String getcBenef() {
		return cBenef;
	}
	public void setcBenef(String cBenef) {
		this.cBenef = cBenef;
	}
	public String getExtIpi() {
		return extIpi;
	}
	public void setExtIpi(String extIpi) {
		this.extIpi = extIpi;
	}
	public String getvFrete() {
		return vFrete;
	}
	public void setvFrete(String vFrete) {
		this.vFrete = vFrete;
	}
	public String getvSeg() {
		return vSeg;
	}
	public void setvSeg(String vSeg) {
		this.vSeg = vSeg;
	}
	public String getvOutro() {
		return vOutro;
	}
	public void setvOutro(String vOutro) {
		this.vOutro = vOutro;
	}
	public String getvDesc() {
		return vDesc;
	}
	public void setvDesc(String vDesc) {
		this.vDesc = vDesc;
	}
	public String getCest() {
		return cest;
	}
	public void setCest(String cest) {
		this.cest = cest;
	}
	public String getcProd() {
		return cProd;
	}
	public void setcProd(String cProd) {
		this.cProd = cProd;
	}
	public String getcEan() {
		return cEan;
	}
	public void setcEan(String cEan) {
		this.cEan = cEan;
	}
	public String getxProd() {
		return xProd;
	}
	public void setxProd(String xProd) {
		this.xProd = xProd;
	}
	public String getNcm() {
		return ncm;
	}
	public void setNcm(String ncm) {
		this.ncm = ncm;
	}
	public Integer getCfop() {
		return cfop;
	}
	public void setCfop(Integer cfop) {
		this.cfop = cfop;
	}
	public String getuCom() {
		return uCom;
	}
	public void setuCom(String uCom) {
		this.uCom = uCom;
	}
	public String getqCom() {
		return qCom;
	}
	public void setqCom(String qCom) {
		this.qCom = qCom;
	}
	public String getvUnCom() {
		return vUnCom;
	}
	public void setvUnCom(String vUnCom) {
		this.vUnCom = vUnCom;
	}
	public String getvProd() {
		return vProd;
	}
	public void setvProd(String vProd) {
		this.vProd = vProd;
	}
	public String getcEanTrib() {
		return cEanTrib;
	}
	public void setcEanTrib(String cEanTrib) {
		this.cEanTrib = cEanTrib;
	}
	public String getuTrib() {
		return uTrib;
	}
	public void setuTrib(String uTrib) {
		this.uTrib = uTrib;
	}
	public String getqTrib() {
		return qTrib;
	}
	public void setqTrib(String qTrib) {
		this.qTrib = qTrib;
	}
	public String getvUnTrib() {
		return vUnTrib;
	}
	public void setvUnTrib(String vUnTrib) {
		this.vUnTrib = vUnTrib;
	}
	public String getIndTot() {
		return indTot;
	}
	public void setIndTot(String indTot) {
		this.indTot = indTot;
	}
	public String getIndEscala() {
		return indEscala;
	}
	public void setIndEscala(String indEscala) {
		this.indEscala = indEscala;
	}
}
