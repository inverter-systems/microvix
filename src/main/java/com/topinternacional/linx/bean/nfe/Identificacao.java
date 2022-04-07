package com.topinternacional.linx.bean.nfe;

import java.util.Date;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("ide")
public class Identificacao {

	@XStreamAlias("cUF")
	private Integer cUf;
	@XStreamAlias("cNF")
	private String cNf;
	private String natOp;
	private Integer mod;
	private Integer serie;
	private Integer nNF;
	private String dhEmi;
	private Date dhSaiEnt;
	private Integer tpNF;
	private Integer idDest;
	private Integer cMunFG;
	private Integer tpImp;
	private Integer tpEmis;
	private Integer cDV;
	private Integer tpAmb;
	private Integer finNFe;
	private Integer indFinal;
	private Integer indPres;
	private Integer indIntermed;
	private Integer procEmi;
	private String verProc;
	private Date dhCont;
	private String xJust;
	
	@XStreamAlias("NFref")
	private NFref nfRef;
	
	public String getcNF() {
		return cNf;
	}
	public void setcNF(String cNF) {
		this.cNf = cNF;
	}
	public Date getDhSaiEnt() {
		return dhSaiEnt;
	}
	public void setDhSaiEnt(Date dhSaiEnt) {
		this.dhSaiEnt = dhSaiEnt;
	}
	public Integer getIndIntermed() {
		return indIntermed;
	}
	public void setIndIntermed(Integer indIntermed) {
		this.indIntermed = indIntermed;
	}
	public Date getDhCont() {
		return dhCont;
	}
	public void setDhCont(Date dhCont) {
		this.dhCont = dhCont;
	}
	public String getxJust() {
		return xJust;
	}
	public void setxJust(String xJust) {
		this.xJust = xJust;
	}
	public Integer getcUf() {
		return cUf;
	}
	public void setcUf(Integer cUf) {
		this.cUf = cUf;
	}
	public String getNatOp() {
		return natOp;
	}
	public void setNatOp(String natOp) {
		this.natOp = natOp;
	}
	public Integer getMod() {
		return mod;
	}
	public void setMod(Integer mod) {
		this.mod = mod;
	}
	public Integer getSerie() {
		return serie;
	}
	public void setSerie(Integer serie) {
		this.serie = serie;
	}
	public Integer getnNF() {
		return nNF;
	}
	public void setnNF(Integer nNF) {
		this.nNF = nNF;
	}
	public String getDhEmi() {
		return dhEmi;
	}
	public void setDhEmi(String dhEmi) {
		this.dhEmi = dhEmi;
	}
	public Integer getTpNF() {
		return tpNF;
	}
	public void setTpNF(Integer tpNF) {
		this.tpNF = tpNF;
	}
	public Integer getIdDest() {
		return idDest;
	}
	public void setIdDest(Integer idDest) {
		this.idDest = idDest;
	}
	public Integer getcMunFG() {
		return cMunFG;
	}
	public void setcMunFG(Integer cMunFG) {
		this.cMunFG = cMunFG;
	}
	public Integer getTpImp() {
		return tpImp;
	}
	public void setTpImp(Integer tpImp) {
		this.tpImp = tpImp;
	}
	public Integer getTpEmis() {
		return tpEmis;
	}
	public void setTpEmis(Integer tpEmis) {
		this.tpEmis = tpEmis;
	}
	public Integer getcDV() {
		return cDV;
	}
	public void setcDV(Integer cDV) {
		this.cDV = cDV;
	}
	public Integer getTpAmb() {
		return tpAmb;
	}
	public void setTpAmb(Integer tpAmb) {
		this.tpAmb = tpAmb;
	}
	public Integer getFinNFe() {
		return finNFe;
	}
	public void setFinNFe(Integer finNFe) {
		this.finNFe = finNFe;
	}
	public Integer getIndFinal() {
		return indFinal;
	}
	public void setIndFinal(Integer indFinal) {
		this.indFinal = indFinal;
	}
	public Integer getIndPres() {
		return indPres;
	}
	public void setIndPres(Integer indPres) {
		this.indPres = indPres;
	}
	public Integer getProcEmi() {
		return procEmi;
	}
	public void setProcEmi(Integer procEmi) {
		this.procEmi = procEmi;
	}
	public String getVerProc() {
		return verProc;
	}
	public void setVerProc(String verProc) {
		this.verProc = verProc;
	}
}
