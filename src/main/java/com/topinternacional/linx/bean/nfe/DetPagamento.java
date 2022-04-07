package com.topinternacional.linx.bean.nfe;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("detPag")
public class DetPagamento {
	
	private String indPag;
	private String tPag;
	private String vPag;
    private Card card = new Card();
	
	public String getIndPag() {
		return indPag;
	}
	public void setIndPag(String indPag) {
		this.indPag = indPag;
	}
	public String gettPag() {
		return tPag;
	}
	public void settPag(String tPag) {
		this.tPag = tPag;
	}
	public String getvPag() {
		return vPag;
	}
	public void setvPag(String vPag) {
		this.vPag = vPag;
	}
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	
}
