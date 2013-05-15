package com.structis.vip.shared.model;

public class DelegationMdlModel extends BaseModelDataActivable {

	private static final long serialVersionUID = 6807380804987897136L;
	public static final String DEM_ID = "id";
	public static final String DEM_LANGUAGE = "language";
	public static final String DEM_PERIMETRE_TYPE = "perimetreType";
	public static final String DEM_DELEGATION_NATURE = "delegationNature";
	public static final String DEM_ENTIE = "entite";
	public static final String DEM_COLLABORATOR_TYPE = "collaborateurType";
	public static final String DEM_DEM_ID = "demId";	
	public static final String DEM_DEM_GROUP = "group";
	public static final String DEM_MULTIPLE_DELEGATION = "hasMultipleDelegation";
	public static final String DEM_MULTIPLE_DELEGATAIRE = "hasMultipleDelegataire";
	public static final String DEM_SUB_DELEGATION = "subDelegation";	
	
		
	@SuppressWarnings("unused")
	private LanguageModel languageModel;
	
	@SuppressWarnings("unused")
	private PerimetreTypeModel perimetreTypeModel;
	
	@SuppressWarnings("unused")
	private DelegationNatureModel delegationNatureModel;
	
	@SuppressWarnings("unused")
	private CollaborateurTypeModel collaborateurTypeModel;	
	
	public Integer getId() {
		return get(DEM_ID);
	}

	public void setId(Integer id) {
		set(DEM_ID, id);
	}
	
	public LanguageModel getLanguage() {
		return get(DEM_LANGUAGE);
	}
	public void setLanguage(LanguageModel language) {
		set(DEM_LANGUAGE, language);
	}
	public PerimetreTypeModel getPerimetreType() {
		return get(DEM_PERIMETRE_TYPE);
	}
	public void setPerimetreType(PerimetreTypeModel perimetre) {
		set(DEM_PERIMETRE_TYPE, perimetre);
	}
	public DelegationNatureModel getDelegationNature() {
		return get(DEM_DELEGATION_NATURE);
	}
	public void setDelegationNature(DelegationNatureModel delegationNature) {
		set(DEM_DELEGATION_NATURE, delegationNature);
	}
	public CollaborateurTypeModel getCollaborateurType() {
		return get(DEM_COLLABORATOR_TYPE);
	}
	public void setCollaborateurType(CollaborateurTypeModel collaborateurType) {
		set(DEM_COLLABORATOR_TYPE, collaborateurType);
	}

	public EntiteModel getEntite() {
		return get(DEM_ENTIE);
	}

	public void setEntite(EntiteModel entite) {
		set(DEM_ENTIE, entite);
	}

	public Integer getDemId() {
		return get(DEM_DEM_ID);
	}

	public void setDemId(Integer demId) {
		set(DEM_DEM_ID, demId);
	}
	
	public Integer getGroup() {
		return get(DEM_DEM_GROUP);
	}

	public void setGroup(Integer group) {
		set(DEM_DEM_GROUP, group);
	}
	public Integer getHasMultipleDelegation() {
		return get(DEM_MULTIPLE_DELEGATION);
	}

	public void setHasMultipleDelegation(Integer hasMultipleDelegation) {
		set(DEM_MULTIPLE_DELEGATION, hasMultipleDelegation);
	}
	public void setHasMultipleDelegataire(Integer hasMultipleDelegataire) {
		set(DEM_MULTIPLE_DELEGATAIRE, hasMultipleDelegataire);
	}

	public Integer getHasMultipleDelegataire() {
		return get(DEM_MULTIPLE_DELEGATAIRE);
	}
	public Integer getSubDelegation() {
		return get(DEM_SUB_DELEGATION);
	}

	public void setSubDelegation(Integer subDelegation) {
		set(DEM_SUB_DELEGATION, subDelegation);
	}
}