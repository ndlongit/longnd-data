package com.structis.fichesst.shared.dto;

import java.util.List;

public class FicheTransfertppDto extends AbstractDto {

	public static final String CHANTIER = "chantier";

	public static final String REF_TRANSFERT_PP = "refTransfertPp";

	private static final String OBJECTIF = "objectif";
	
	public static final String LIGTRANSFERTPPS = "ligTransfertPps";

	@SuppressWarnings("unused")
	private SimpleDto refTransfertPp;

	@SuppressWarnings("unused")
	private ChantierModel chantier;

	@SuppressWarnings("unused")
	private List<LigTransfertppModel> ligTransfertPps;
	
	public ChantierModel getChantier() {
		return get(CHANTIER);
	}

	public void setChantier(ChantierModel chantier) {
		set(CHANTIER, chantier);
	}

	public SimpleDto getRefTransfertPp() {
		return get(REF_TRANSFERT_PP);
	}

	public void setRefTransfertPp(SimpleDto refTransfertPp) {
		set(REF_TRANSFERT_PP, refTransfertPp);
	}

	public Double getObjectif() {
		return get(OBJECTIF, 0.0);
	}

	public void setObjectif(Double objectif) {
		set(OBJECTIF, objectif);
	}
	
	public List<LigTransfertppModel> getLigTransfertPps() {
		return get(LIGTRANSFERTPPS);
	}

	public void setLigTransfertPps(List<LigTransfertppModel> ligTransfertPps) {
		set(LIGTRANSFERTPPS, ligTransfertPps);
	}

	@Override
	public int hashCode() {
		int hashValue = 0;
		Integer chantierId = getChantier().getId();
		if( chantierId != null && chantierId > 0 ) {
			hashValue += chantierId.hashCode();
		}
		Integer transfertPpId = getRefTransfertPp().getId();
		if( transfertPpId != null && transfertPpId > 0 ) {
			hashValue += transfertPpId.hashCode();
		}

		return hashValue;
	}

	@Override
	public boolean equals(Object obj) {
		if( !(obj instanceof FicheTransfertppDto) ) {
			return false;
		}

		FicheTransfertppDto other = (FicheTransfertppDto) obj;

		Integer chantierId = getChantier().getId();
		Integer transfertPpId = getRefTransfertPp().getId();

		Integer chantierId2 = other.getChantier().getId();
		Integer transfertPpId2 = getRefTransfertPp().getId();

		return(chantierId != null && chantierId.equals(chantierId2) && transfertPpId != null && transfertPpId.equals(transfertPpId2));
	}
}
