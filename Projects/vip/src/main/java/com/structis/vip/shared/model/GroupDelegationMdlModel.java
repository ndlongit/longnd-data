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
        return this.get(GROUP_DEM_GROUP);
    }

    public void setGroup(Integer group) {
        this.set(GROUP_DEM_GROUP, group);
    }

    public EntiteModel getEntite() {
        return this.get(GROUP_DEM_ENTITE);
    }

    public void setEntite(EntiteModel entite) {
        this.set(GROUP_DEM_ENTITE, entite);
    }

    public LanguageModel getLanguage() {
        return this.get(GROUP_DEM_LANGUAGE);
    }

    public void setLanguage(LanguageModel language) {
        this.set(GROUP_DEM_LANGUAGE, language);
    }

    public DelegationNatureModel getDelegationNature() {
        return this.get(GROUP_DEM_NATURE);
    }

    public void setDelegationNature(DelegationNatureModel delegationNature) {
        this.set(GROUP_DEM_NATURE, delegationNature);
    }

    public String getPerimetreType() {
        return this.get(GROUP_DEM_PERIMETRE_TYPE);
    }

    public void setPerimetreType(String perimetreType) {
        this.set(GROUP_DEM_PERIMETRE_TYPE, perimetreType);
    }

    public String getDelegantType() {
        return this.get(GROUP_DEM_DELEGANT_TYPE);
    }

    public void setDelegantType(String delegantType) {
        this.set(GROUP_DEM_DELEGANT_TYPE, delegantType);
    }

    public Integer getHasMultipleDelegation() {
        return this.get(GROUP_HAS_MULTIPLE_DELEGATION);
    }

    public void setHasMultipleDelegation(Integer val) {
        this.set(GROUP_HAS_MULTIPLE_DELEGATION, val);
    }

    public Integer getHasMultipleDelegataire() {
        return this.get(GROUP_HAS_MULTIPLE_DELEGATAIRE);
    }

    public void setHasMultipleDelegataire(Integer val) {
        this.set(GROUP_HAS_MULTIPLE_DELEGATAIRE, val);
    }

    public Integer getSubDelegation() {
        return this.get(GROUP_SUB_DELEGATION);
    }

    public void setSubDelegation(Integer val) {
        this.set(GROUP_SUB_DELEGATION, val);
    }
}
