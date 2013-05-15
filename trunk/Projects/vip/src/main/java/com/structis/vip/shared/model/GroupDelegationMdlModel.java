package com.structis.vip.shared.model;

public class GroupDelegationMdlModel extends BaseModelDataActivable {

	private static final long serialVersionUID = 1L;
	public static final String GROUP_DEM_GROUP = "group";
	public static final String GROUP_DEM_ENTITE = "entite";
	public static final String GROUP_DEM_NATURE = "nature";
	public static final String GROUP_DEM_LANGUAGE = "language";
	public static final String GROUP_DEM_PERIMETRE_TYPE = "perimetreType";
	public static final String GROUP_DEM_DELEGANT_TYPE = "delegantType";
	public static final String GROUP_HAS_MULTIPLE_DELEGATION = "hasMultipleDelegation";
	public static final String GROUP_HAS_MULTIPLE_DELEGATAIRE = "hasMultipleDelegataire";
	public static final String GROUP_SUB_DELEGATION = "subDelegation";

	public Integer getGroup() {
		return get(GROUP_DEM_GROUP);
	}

	public void setGroup(Integer group) {
		set(GROUP_DEM_GROUP, group);
	}

	public EntiteModel getEntite() {
		return get(GROUP_DEM_ENTITE);
	}

	public void setEntite(EntiteModel entite) {
		set(GROUP_DEM_ENTITE, entite);
	}

	public LanguageModel getLanguage() {
		return get(GROUP_DEM_LANGUAGE);
	}

	public void setLanguage(LanguageModel language) {
		set(GROUP_DEM_LANGUAGE, language);
	}

	public DelegationNatureModel getDelegationNature() {
		return get(GROUP_DEM_NATURE);
	}

	public void setDelegationNature(DelegationNatureModel delegationNature) {
		set(GROUP_DEM_NATURE, delegationNature);
	}
	
	public String getPerimetreType() {
		return get(GROUP_DEM_PERIMETRE_TYPE);
	}

	public void setPerimetreType(String perimetreType) {
		set(GROUP_DEM_PERIMETRE_TYPE, perimetreType);
	}

	public String getDelegantType() {
		return get(GROUP_DEM_DELEGANT_TYPE);
	}

	public void setDelegantType(String delegantType) {
		set(GROUP_DEM_DELEGANT_TYPE, delegantType);
	}
	public Integer getHasMultipleDelegation() {
		return get(GROUP_HAS_MULTIPLE_DELEGATION);
	}

	public void setHasMultipleDelegation(Integer val) {
		set(GROUP_HAS_MULTIPLE_DELEGATION, val);
	}
	public Integer getHasMultipleDelegataire() {
		return get(GROUP_HAS_MULTIPLE_DELEGATAIRE);
	}

	public void setHasMultipleDelegataire(Integer val) {
		set(GROUP_HAS_MULTIPLE_DELEGATAIRE, val);
	}
	public Integer getSubDelegation() {
		return get(GROUP_SUB_DELEGATION);
	}

	public void setSubDelegation(Integer val) {
		set(GROUP_SUB_DELEGATION, val);
	}
}
