package com.structis.fichesst.shared.dto;

public class SocieteDto extends AbstractDto {

	public static final String NOM = "nom";

	public static final String CHANTIER = "chantier";

	@SuppressWarnings("unused")
	private ChantierModel chantier;

	public String getNom() {
		return get(NOM);
	}

	public void setNom(String name) {
		set(NOM, name);
	}

	public ChantierModel getChantier() {
		return get(CHANTIER);
	}

	public void setChantier(ChantierModel chantier) {
		set(CHANTIER, chantier);
	}
}
