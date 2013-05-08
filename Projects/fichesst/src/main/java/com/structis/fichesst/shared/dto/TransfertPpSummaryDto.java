package com.structis.fichesst.shared.dto;

import java.util.List;

public class TransfertPpSummaryDto extends AbstractDto {

	public static final String LABEL = "label";

	public static final String OBJECTIVE = "objective";

	public static final String OBJ = "obj";

	public static final String DEVERS = "devers";

	public static final String RD = "rd";

	public static final String TS = "ts";

	public static final String TOTAL = "total";

	public static final String CHANTIER_ID = "chantierId";

	public static final String REF_TRANSFERT_PP_ID = "refTransfertPpId";

	private List<LigTransfertppModel> ligTransfertPps;

	public TransfertPpSummaryDto(Integer chantierId, Integer transfertPpId, String label) {
		super();
		setChantierId(chantierId);
		setRefTransfertPpId(transfertPpId);
		setLabel(label);
	}
	
	public TransfertPpSummaryDto() {
		super();
	}


	public String getLabel() {
		return get(LABEL);
	}

	public void setLabel(String label) {
		set(LABEL, label);
	}

	public List<LigTransfertppModel> getLigTransfertPps() {
		return ligTransfertPps;
	}

	public void setLigTransfertPps(List<LigTransfertppModel> ligTransfertPps) {
		calculateValues(ligTransfertPps);
	}

	public Double getObjective() {
		Object obj = get(OBJECTIVE);
		if( obj == null ) {
			return 0.0;
		}
		return get(OBJECTIVE);
	}

	public void setObjective(Double objective) {
		set(OBJECTIVE, objective);
	}

	public Double getObj() {
		Object obj = get(OBJ);
		if( obj == null ) {
			return 0.0;
		}
		return get(OBJ);
	}

	public void setObj(Double obj) {
		set(OBJ, obj);
	}

	public Double getDevers() {
		Object obj = get(DEVERS);
		if( obj == null ) {
			return 0.0;
		}
		return get(DEVERS);
	}

	public void setDevers(Double devers) {
		set(DEVERS, devers);
	}

	public Double getRd() {
		Object obj = get(RD);
		if( obj == null ) {
			return 0.0;
		}
		return get(RD);
	}

	public void setRd(Double rd) {
		set(RD, rd);
	}

	public Double getTs() {
		Object obj = get(TS);
		if( obj == null ) {
			return 0.0;
		}
		return get(TS);
	}

	public void setTs(Double ts) {
		set(TS, ts);
	}

	public Integer getChantierId() {
		return get(CHANTIER_ID);
	}

	public void setChantierId(Integer chantierId) {
		set(CHANTIER_ID, chantierId);
	}

	public Integer getRefTransfertPpId() {
		return get(REF_TRANSFERT_PP_ID);
	}

	public void setRefTransfertPpId(Integer transfertPpId) {
		set(REF_TRANSFERT_PP_ID, transfertPpId);
	}

	private void calculateValues(List<LigTransfertppModel> ligModels) {
		double totalObjPositive = 0.0;
		double totalObjNegative = 0.0;
		double amountObj = 0.0;
		double totalDeversPositive = 0.0;
		double totalDeversNegative = 0.0;
		double amountDevers = 0.0;
		double totalTsPositive = 0.0;
		double totalTsNegative = 0.0;
		double amountTs = 0.0;
		double totalRDPositive = 0.0;
		double totalRDNegative = 0.0;
		double amountRD = 0.0;

		if (ligModels != null) {
			for (LigTransfertppModel lig : ligModels) {

				Integer typeId = lig.getRefTypeBudjConf().getId();
				Integer quantity = lig.getQuantite();
				Double pu = lig.getPu();
				String deVers = lig.getDeVers();

				if (typeId == 1 && deVers.equalsIgnoreCase("vers")) {
					totalObjNegative += pu * quantity * (-1);
				}
				if (typeId == 1 && deVers.equalsIgnoreCase("de")) {
					totalObjPositive += pu * quantity;
				}
				if (typeId == 2 && deVers.equalsIgnoreCase("vers")) {
					totalDeversNegative += pu * quantity * (-1);
				}
				if (typeId == 2 && deVers.equalsIgnoreCase("de")) {
					totalDeversPositive += pu * quantity;
				}
				if (typeId == 3 && deVers.equalsIgnoreCase("vers")) {
					totalRDNegative += pu * quantity * (-1);
				}
				if (typeId == 3 && deVers.equalsIgnoreCase("de")) {
					totalRDPositive += pu * quantity;
				}
				if (typeId == 4 && deVers.equalsIgnoreCase("vers")) {
					totalTsNegative += pu * quantity * (-1);
				}
				if (typeId == 4 && deVers.equalsIgnoreCase("de")) {
					totalTsPositive += pu * quantity;
				}
			}
		}
		amountObj = totalObjNegative + totalObjPositive;
		amountDevers = totalDeversNegative + totalDeversPositive;
		amountRD = totalRDNegative + totalRDPositive;
		amountTs = totalTsNegative + totalTsPositive;

		setObj(amountObj);
		setDevers(amountDevers);
		setTs(amountTs);
		setRd(amountRD);
	}

	private Double getTotal() {
		return getObj() + getDevers() + getRd() + getTs();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <X> X get(String property) {
		Object result = null;
		if( TOTAL.equals(property) ) {
			result = getTotal();
		}
		else {
			result = super.get(property);
		}
		return (X) result;
	}
}
