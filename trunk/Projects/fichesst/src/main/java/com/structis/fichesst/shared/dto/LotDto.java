package com.structis.fichesst.shared.dto;

public class LotDto extends AbstractDto {

	public static final String NAME = "name";

	public static final String CHANTIER = "chantier";

	public String getName() {
		return get(NAME);
	}

	public void setName(String name) {
		set(NAME, name);
	}

	public ChantierModel getChantier() {
		return get(CHANTIER);
	}

	public void setChantier(ChantierModel chantier) {
		set(CHANTIER, chantier);
	}
}
