package com.topinternacional.linx.enun;

public enum Sistemas {
	NL(1),
	MICROVIX(2),
	PCI(3);

	private Integer id;

	private Sistemas(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
}
