package com.structis.fichesst.shared.dto;

public class ConductorDto extends AbstractDto {
	public static final String NAME = "name";

	public static final String PRENAME = "preName";

	public static final String IDSITRAVAUX = "idSiTravaux";

	public static final String IDCHANTIER = "idChantier";

	public String getName() {
		return get(NAME);
	}

	public void setName(String name) {
		set(NAME, name);
	}

	public String getPreName() {
		return get(PRENAME);
	}

	public void setPreName(String preName) {
		set(PRENAME, preName);
	}

	public String getIdSiTravaux() {
		return get(IDSITRAVAUX);
	}

	public void setIdSiTravaux(String idSiTravaux) {
		set(IDSITRAVAUX, idSiTravaux);
	}

	public Integer getIdChantier() {
		Object obj = get(IDCHANTIER);
		if( obj == null ) {
			return 0;
		}
		return get(IDCHANTIER);
	}

	public void setIdChantier(Integer idChantier) {
		set(IDCHANTIER, idChantier);
	}
}
