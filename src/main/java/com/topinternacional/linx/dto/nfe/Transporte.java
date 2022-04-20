package com.topinternacional.linx.dto.nfe;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("transp")
public class Transporte {
	
	private String modFrete;

	private Vol vol = new Vol();
	
	public String getModFrete() {
		return modFrete;
	}
	public void setModFrete(String modFrete) {
		this.modFrete = modFrete;
	}
	public Vol getVol() {
		return vol;
	}
	public void setVol(Vol vol) {
		this.vol = vol;
	}
}
